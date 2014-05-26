WhoisLibrary
==================

This is a draft of a java library that provides a basic and simple api to perform whois requests. 

The requests that actually it can perform are only for the following domain types: 
* .com
* .net
* .edu
* .org 
* .it

This library is easy to use, and easy to extend, if you want to add a support for a new tld, you just have to extend the Template class. The whois URL is passed to the constructor as argument, you have to load operations using the loadOperations method.

Then, you have to add an entry into the file whois.properties, in the following format:

tld=fullclassName

For example:
```Java
org=org.whoislibrary.templates.TemplateOrg
```

Usage
-----

You can test the library launching the WhoisMain class:

```Bash
java WhoisMain
```

with no arguments it performs just three predefined whois query (for test)

If you specify a domain name, if the tld is implemented it gives you the result of your query (and then the three test queries):

```Bash
java WhoisMain yourdomain.com
```

Documentation
-------------
The documentation is available here: http://inuyasha82.github.io/WhoisLibrary/doc/index.html


Copyright and Licence
---------------------
Author Ivan Gualandri

Contributor: Dario Casalinuovo

The library is released under the terms of GNU/GPL v3 License.

[![Flattr this git repo](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=italialinux&url=https://github.com/inuyasha82/WhoisLibrary&title=WhoisLibrary&language=&tags=github&category=software)
