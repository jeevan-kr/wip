from django.template.loader import get_template
from django.template import Context, Template
from django.shortcuts import render_to_response
from django.http import HttpResponse

import datetime

def current_datetime(request):
    now = datetime.datetime.now()
    
    #html = "<html><body>It is now %s.</body></html>" % now
    
    #t = Template("<html> <body> It is now {{current_date}}.</body></html>")
    
    #t = get_template('current_datetime.html')
    #html = t.render(Context({'current_time':now}))
    #return HttpResponse(html)
    return render_to_response('current_datetime.html', {'current_time':now})


def hours_ahead(request, offset):
    offset = int(offset)
    dt = datetime.datetime.now() + datetime.timedelta(hours = offset)
    html = "<html> <body> In %s hour(s), it will be %s.</body></html>"%(offset, dt)
    return HttpResponse(html)

def book_list(request):
    db = MySQLdb.connect(user='me', db='mydb', passwd='secret', host='localhost')
    cursor = db.cursor()
    cursor.execute('SELECT name FROM books ORDER BY name')
    names = [row[0] for row in cursor.fetchall()]
    db.close()
    return render_to_response('book_list.html', {'names': names})
