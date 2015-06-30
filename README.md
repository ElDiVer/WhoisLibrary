WhoisLibrary
==================

This is a java library that provides a simple yet powerful api to perform whois requests. It's currently in alpha status.

The requests it can perform actually are only for the following domains: 
* .com
* .edu
* .it
* .info
* .net
* .org 

WhoisLibrary doesn't only let you to get the raw result of a whois request. It's based on a templating system allowing to parse the raw data, the parser provide the results in a well-structured form by filling the WhoisEntry object. Since formats and responses may vary greatly, it's not possible to parse and organize all data from all TLDs. WhoisLibrary has been designed as a trade-off between data relevance, uniformity and completeness, but without bottlenecks preventing to create a custom version of the library that feets the specifical needs you have. Nonetheless WhoisEntry carry also with it the raw version of the data, that can get using the method WhoisEntry::getRawData(). This will allow to retrieve additional data without the need to modify the library at all.

Add support for a new TLD
-----------------------

The library template system is easy to use, and easy to extend, if you want to add a support for a new TLD, you have to just extend the Template class. Parse operations are filled into an array of objects derived from ParseOperation. If you add an unsupported domain or make some bugfix, please send us a patch.

There are actually three derived classes :

* StringOperation

It's used to get strings from the input stream.

* DateOperation

It's used to get, parse and convert dates from String to Date.

* SkipOperation

This is optional, and should be use to instruct the parser to ignore a given number of lines.

The whois server URL is passed to the constructor as argument, you have to load operations using the loadOperations method, see the templates directory for more informations.

Once you created your Template, you have to add an entry into the file domain_map.properties, in the following format:

tld=fullclassName

For example:
```Java
org=org.whoislibrary.templates.TemplateOrg
```

TemplateGeneric is a start skeleton to develop a new template.

Compile
-------------
You can create an executable jar using ant build system. If you haven't installed ant yet you can download it from here: 

http://ant.apache.org/bindownload.cgi

Once you have installed it you can compile the program using (remember that if you haven't added the ant bin folder into yuour PATH you must use the full path: /yourpathtoant/bin/ant): 

```Bash
ant jar
```
To build an executable jar.
Or you can just compile classes using: 

```Bash
ant compile
```

The binaries are stored into the bin folder of the project.

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

If you are using the jar file: 
```Bash
java -jar whois.jar yourdomain.com
```

Documentation
-------------
The documentation is available here: http://inuyasha82.github.io/WhoisLibrary/doc/index.html


Copyright and Licence
---------------------
Authors:  Ivan Gualandri
          Dario Casalinuovo

The library is released under the terms of GNU/GPL v3 License. If you like it please donate a coffee!

[![Flattr this git repo](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=italialinux&url=https://github.com/inuyasha82/WhoisLibrary&title=WhoisLibrary&language=&tags=github&category=software)
