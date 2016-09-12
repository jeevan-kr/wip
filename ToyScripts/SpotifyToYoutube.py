#!/usr/bin/python

import httplib2
import os
import sys
import time

import spotipy
from apiclient.discovery import build
from apiclient.errors import HttpError
from oauth2client.client import flow_from_clientsecrets
from oauth2client.file import Storage
from oauth2client.tools import argparser, run_flow

if __name__ == '__main__':
    if len(sys.argv) > 2:
        username = sys.argv[1]
        userPlaylistName = sys.argv[2]
        if len(sys.argv) > 4:
          seconds = sys.argv[3]
          deletePlaylistOption = sys.argv[4] 
    else:
        print "Whoops, need your username!"
        print "usage: python user_playlists.py [username] [Spotify PlaylistName] [no. of seconds]"
        sys.exit()

# The CLIENT_SECRETS_FILE variable specifies the name of a file that contains
# the OAuth 2.0 information for this application, including its client_id and
# client_secret. You can acquire an OAuth 2.0 client ID and client secret from
# the Google Developers Console at
# https://console.developers.google.com/.
# Please ensure that you have enabled the YouTube Data API for your project.
# For more information about using OAuth2 to access the YouTube Data API, see:
#   https://developers.google.com/youtube/v3/guides/authentication
# For more information about the client_secrets.json file format, see:
#   https://developers.google.com/api-client-library/python/guide/aaa_client_secrets
CLIENT_SECRETS_FILE = "client_secrets.json"

# This variable defines a message to display if the CLIENT_SECRETS_FILE is
# missing.
MISSING_CLIENT_SECRETS_MESSAGE = """
WARNING: Please configure OAuth 2.0

To make this sample run you will need to populate the client_secrets.json file
found at:

   %s

with information from the Developers Console
https://console.developers.google.com/

For more information about the client_secrets.json file format, please visit:
https://developers.google.com/api-client-library/python/guide/aaa_client_secrets
""" % os.path.abspath(os.path.join(os.path.dirname(__file__),
                                   CLIENT_SECRETS_FILE))

# This OAuth 2.0 access scope allows for full read/write access to the
# authenticated user's account.
YOUTUBE_READ_WRITE_SCOPE = "https://www.googleapis.com/auth/youtube"
YOUTUBE_API_SERVICE_NAME = "youtube"
YOUTUBE_API_VERSION = "v3"

flow = flow_from_clientsecrets(CLIENT_SECRETS_FILE,
  message=MISSING_CLIENT_SECRETS_MESSAGE,
  scope=YOUTUBE_READ_WRITE_SCOPE)

storage = Storage("%s-oauth2.json" % sys.argv[0])
credentials = storage.get()

if credentials is None or credentials.invalid:
  flags = argparser.parse_args()
  credentials = run_flow(flow, storage, flags)

youtube = build(YOUTUBE_API_SERVICE_NAME, YOUTUBE_API_VERSION,
  http=credentials.authorize(httplib2.Http()))

def show_tracks(results, playlistId):
  for i, item in enumerate(tracks['items']):
    track = item['track']
    artistName = track['artists'][0]['name']
    trackName = track['name']
    #print i, artistName , ",", trackName
    vidId = searchYoutubeForVideo(artistName + " " + trackName)
    print i, 'Found track', trackName,' on Youtube, now adding to playlist' #, vidId 
    addToPlaylist(playlistId, vidId)

# This code creates a new, private playlist in the authorized user's channel.

def createYoutubePlaylist(titleIn):
  playlistStatus = dict(
      privacyStatus="public"
    )
  playlistSnip = dict(
      title = titleIn,
      description = "A private playlist created with the YouTube API v3"
    )
  createBody = dict(
    snippet=playlistSnip,
    status= playlistStatus
  )

  playlists_insert_response = youtube.playlists().insert(
    part="snippet,status",
    body=createBody
  ).execute()
  print 'Created a playlist with title',playlists_insert_response['snippet']['title']
  playlistID = playlists_insert_response["id"]

  return playlistID

def searchYoutubeForVideo(searchTerm):
  max_results = 25
  searchResponse = youtube.search().list(
  q = searchTerm,
  part="id,snippet",
  maxResults = max_results
  #  , body = searchBody
  ).execute()
  items = searchResponse.get('items',[])
  for item in items:
    if item['id']['kind'] == 'youtube#video':
      return item['id']['videoId']

def addToPlaylist(toPlayListId, toVideoId):
  playlists_insert_response = youtube.playlistItems().insert(
  part="snippet",
  body={
    'snippet': {
    #'title': "NewTestingTitle",
      'playlistId': toPlayListId, 
        'resourceId': {
          'kind': 'youtube#video',
          'videoId': toVideoId
        }
            #'position': 0
      }
    }
  ).execute()

def deletePlaylist(playlistIdInput):
  playlistsDeleteResponse = youtube.playlists().delete(
    id = playlistIdInput
  ).execute()
'''
playlistIdX = createYoutubePlaylist('JeevanTest 1118PM')
print "New playlist id: %s" % playlistIdX
vidId = searchYoutubeForVideo("Silent Lucidity Queensryche")
addToPlaylist(playlistIdX, vidId)

i = 60
print 'Sleeping for ',i, 'sec'
time.sleep(i)

print 'Will delete playlist now', playlistIdX
deletePlaylist(playlistIdX)
'''


#PART
# snippet
#BODY
#snippet.playlistId
#snippet.resourceId
#
import spotipy.util as util

#$env:SPOTIPY_CLIENT_ID = '1a711a8c95eb4c5fa9a0c62507150209'
#$env:SPOTIPY_CLIENT_SECRET = 'abf99c752d0b4c9ba3aafb7486d297fe'
#$env:SPOTIPY_REDIRECT_URI = 'https://none.com'

def searchSpotifyListSongs(sp, playlistName):
  results = sp.search(q=playlistName, limit=20)
  print results['tracks']['total']
  for i, t in enumerate(results['tracks']['items']):
    print ' ', i, t['name']
'''
scope = 'user-library-read'
username = 'jeevan.kr'
token = util.prompt_for_user_token(username, scope)
if token:
    sp = spotipy.Spotify(auth=token)
    results = sp.current_user_saved_tracks()
    for item in results['items']:
        track = item['track']
        print track['name'] + ' - ' + track['artists'][0]['name']
else:
    print "Can't get token for", username

spotify = spotipy.Spotify()
name = "Endorfin Rush"
results = spotify.search(q='playlist:' + name, type='playlist')
print results
'''
#sp = spotipy.Spotify()
#searchSpotifyListSongs(sp,"Endorfin Rush")
playlistIdX = createYoutubePlaylist('JeevanTest 1251AM')
#print "New playlist id: %s" % playlistIdX

token = util.prompt_for_user_token(username)

if token:
    sp = spotipy.Spotify(auth=token)
    playlists = sp.user_playlists(username)
    for playlist in playlists['items']:
        if playlist['owner']['id'] == username and playlist['name'] == userPlaylistName:
            print 'Found the playlist', playlist['name'], 'with',playlist['tracks']['total'],'tracks.'
            #print '  total tracks', playlist['tracks']['total']
            results = sp.user_playlist(username, playlist['id'], fields="tracks,next")
            tracks = results['tracks']
            show_tracks(tracks, playlistIdX)
            while tracks['next']:
                tracks = sp.next(tracks)
                show_tracks(tracks, playlistIdX)
else:
    print "Can't get token for", username

print 'Playlist has been created, check it out.'

if deletePlaylistOption == 'Delete':
  i = int(seconds)
  print 'Now, I am going to sleep for ',i, 'sec, before deleting the playlist'
  time.sleep(i)
  print 'Will delete the newly created playlist now', playlistIdX
  deletePlaylist(playlistIdX)
