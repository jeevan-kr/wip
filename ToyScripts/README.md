# ToyScripts
Adhoc personal projects

Spotify to Youtube: The script creates a Youtube playlist from your Spotify playlist. 

Usage: <pre>python SpotifyToYouTube.py [SpotifyUsername] [SpotifyPlaylistName]</pre>

Other Requirements:
* Get API credentials for Youtube and Spotify
* Install Spotipy
  * <pre>pip install spotipy</pre>
* Create client_secrets.json from Youtube credentials

  * Also, create a [client_secrets.json](https://developers.google.com/api-client-library/python/guide/aaa_client_secrets) in the folder with the following format. 
  * <pre>{ 
  "installed": { 
    "client_id": "837647042410-75ifg...usercontent.com",
    "client_secret":"asdlkfjaskd",
    "redirect_uris": ["http://localhost", "urn:ietf:wg:oauth:2.0:oob"],
    "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    "token_uri": "https://accounts.google.com/o/oauth2/token"
  }
}
</pre>
