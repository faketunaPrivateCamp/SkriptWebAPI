# SkriptWebAPI - SKeb

[日本語のREADMEあります](/README_JA.MD)

# Notice

## This addon is under refactor/rewriting

# Disclaimer

I will not responsible for loss, trouble etc. caused by using this plugin. Please use at your own risk.

## Index

- [SkriptWebAPI - SKeb](#skriptwebapi---skeb)
  - [Disclaimer](#disclaimer)
  - [Index](#index)
  - [About](#about)
  - [Features](#features)
  - [Install](#install)
  - [Documentation](#documentation)
    - [Important](#important)
  - [Todo](#todo)


## About

Able to construct API Server, Use web request feature in Skript easily.

## Features

- HTTP API Server
- Web Request

## Install

This plugin requires paper server or paper based server.

This plugin able to load in 1.13 or higher with Skript 2.7.3 or higher. but 1.20.2 or higher is officially supported.

1. Download `SkriptWebAPI-<Version>.jar` from [here](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases).
2. Put your downloaded jar to server plugins folder
3. Run and ready to use

## Documentation (Is not synchronised with this branch)

Example and syntax has provided in Skript Hub

[![SkriptHubViewTheDocs](http://skripthub.net/static/addon/ViewTheDocsButton.png)](http://skripthub.net/docs/?addon=SkriptWebAPI)

### Important

If you use reply effect in if statement, you need `exit` after reply for avoid error
```sk
if (something):
    reply %httpexchange% with %string% and response code 200
    exit
```

Also you need `reply %httpexchange% as not found` in last line of event
```sk
on receive get request:
    set {_request} to event-request

    if (something):
        reply {_request} with %string% and response code 200
        exit
    
    reply {_request} as not found
```

If someone know forcing this syntax above please open issue.

## Todo

- Done refactoring/rewriting the entire code.