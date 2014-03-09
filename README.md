WhoisLibrary
==================

This is a draft of a java library that provides a basic and simple api to perform whois requests. 

The requests that actually it can perform are only for the following domain types: 
* .com
* .org 
* .it

This library is easy to use, and easy to extend, if you want to add a support for a new tld, you just need to extend the WhoisAbstract class, and override the following methods:
* public WhoisEntry parseResponse(BufferedReader queryResult);
* public String getWhoisURL();

Copyright and License
---------------------
Author Ivan Gualandri

The library is released under the therms of GNU/GPL v3 License.

[![Flattr this git repo](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=italialinux&url=https://github.com/inuyasha82/WhoisLibrary&title=WhoisLibrary&language=&tags=github&category=software)
