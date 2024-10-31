# SkriptWebAPI - Skweapi

[English] [[日本語]](/README_JA.MD)
# Disclaimer

I will not be responsible for loss, trouble etc. caused by using this plugin. Please use at your own risk.

## Index

- [SkriptWebAPI - Skweapi](#skriptwebapi---skweapi)
  - [Disclaimer](#disclaimer)
  - [Index](#index)
  - [About](#about)
  - [Features](#features)
  - [Install](#install)
  - [Documentation](#documentation)
    - [Important](#important)
  - [Todo](#todo)


## About

Simple HTTP Server and Web request feature in Skript easily.

## Features

- HTTP Server
- Web Request

## Install

This plugin requires paper server or paper based server.

This plugin able to load in 1.13 or higher with Skript 2.7.3 or higher. but only 1.20.2 or higher is officially supported.

1. Download `SkriptWebAPI-<Version>.jar` from [here](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases).
2. Put your downloaded jar to server plugins folder
3. Run and ready to use

## Documentation

Example and syntax has provided in Skript Hub

[![SkriptHubViewTheDocs](http://skripthub.net/static/addon/ViewTheDocsButton.png)](http://skripthub.net/docs/?addon=SkriptWebAPI)

### Important

If you use reply effect not in end of event you need `exit` after reply for avoid error
```sk
if (something):
    reply %httpexchange% with %string% and response code 200
    exit
if (something2):
    reply %httpexchange% with %string% and response code 202
    exit
    
```

You should respond the HTTP request otherwise HTTP clients keep waiting for your response.
```sk
on receive get request:
    set {_request} to event-request

    if (something):
        reply {_request} with %string% and response code 200
        exit
    
    reply {_request} with %string% and response code 500
```

If someone know forcing this syntax above please open issue.

## Todo

- None