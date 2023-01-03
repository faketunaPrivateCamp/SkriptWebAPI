# SkriptWebAPI - SKeb

[日本語のREADMEあります](/README_JA.MD)

## Disclaimer

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

Allow to use API server feature, Web Request feature easily on Minecraft servers with Skript.

## Features

- Allow to build HTTP API Server
- Can send Web Request

## Install

Might not be work in spigot server. Please use Paper server or Paper fork server.

Now only work with 1.19.3 server. Older version is supporting soon.

1. Download `SkriptWebAPI-<Version>.jar` from [here](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases). Lager number mean recent.
2. Put your downloaded jar to server plugins folder
3. Run and ready to use

## Documentation

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

- Test and allow to load in 1.12.2 ~ 1.19.2