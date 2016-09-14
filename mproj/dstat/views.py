from django.shortcuts import render
from django.conf import settings

# Create your views here.

def test(request):
	return render(request, 'base.html', {'settings':settings})