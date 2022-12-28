# SkriptWebAPI - SKeb

[English readme available](/README.MD)

# 免責事項


## 目次

- [SkriptWebAPI - SKeb](#skriptwebapi---skeb)
- [免責事項](#免責事項)
  - [目次](#目次)
  - [概要](#概要)
  - [機能](#機能)
  - [インストール](#インストール)
  - [使い方とか](#使い方とか)
    - [重要](#重要)
    - [Effects](#effects)
    - [Events](#events)
    - [Event values in all receive request event](#event-values-in-all-receive-request-event)
    - [Expressions](#expressions)
- [Example](#example)
  - [やることリスト](#やることリスト)


## 概要

Skriptを使用してMinecraftサーバーにAPIサーバー設置したりとかWeb Request送れるようにします。

## 機能

- HTTPのAPIサーバー建てられます
- Web request送れます
## インストール

Spigotサーバーではおそらく動作しません。 Paperサーバーまたはその派生サーバーをご利用ください。

現在は1.19.3でのみ動作します。今後古いバージョンもサポートする予定です。

1. [ここ](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases) から `SkriptWebAPI-<バージョン>.jar` をダウンロードしてください。 数字が大きい方が新しいです。
2. ダウンロードしたjarファイルをサーバーのpluginsフォルダに入れてください
3. 起動したら使用可能になります

## 使い方とか

不完全な可能性あり

### 重要

IF文の中にreplyが含まれている場合その処理の後のバグを回避するために `exit` を入れてください。
```sk
if (something):
    reply %httpexchange% with %string% and response code 200
    exit
```

また `reply %httpexchange% as not found` をイベントの最後の行に入れてください。
```sk
on receive get request:
    set {_request} to event-request

    if (something):
        reply {_request} with %string% and response code 200
        exit
    
    reply {_request} as not found
```

もしも上の文法を強制させる方法を知っている人が居たらissueを開いていただくと幸いです


### Effects
```sk
0.0.1+ [SKeb] reply %httpexchange% as (not found|404)
0.0.1+ [SKeb] reply %httpexchange% [to [the] request sender] [(with|and) body %-string%] [(with|and) response code %-integer%]
0.0.1+ [SKeb] set [the] %httpexchange%['s] response header %string% to %string%
0.0.1+ [SKeb] start http [api] server [in port %-integer%]
0.0.1+ [SKeb] stop http [api] server
```

### Events
```sk
0.0.1+ [SKeb] [server] receive get request
0.0.1+ [SKeb] [server] receive post request
0.0.1+ [SKeb] [server] receive put request
0.0.1+ [SKeb] [server] receive delete request
```

### Event values in all receive request event
```sk
0.0.1+ [SKeb] event-body
0.0.1+ [SKeb] event-header
0.0.1+ [SKeb] event-useragent
0.0.1+ [SKeb] event-agent
0.0.1+ [SKeb] event-senderaddress
0.0.1+ [SKeb] event-address
0.0.1+ [SKeb] event-contextpath
0.0.1+ [SKeb] event-path
```

### Expressions
```sk
0.0.1+ [SKeb] %contextpath% parsed as text
0.0.1+ [SKeb] %senderaddress% parsed as text
0.0.1+ [SKeb] %useragent% parsed as text
0.0.1+ [SKeb] %body% parsed as text
```


# Example
```sk
on receive get request:
    set {_request} to event-request
    set {_path} to event-path parsed as text
    set {_body} to ""

    if ({_path} is "/mirror"):
        set {_body} to event-body parsed as text
        set {_request}'s response header "Content-Type" to "application/json"
        reply {_request} with body {_body} and response code 200
        exit
        
    if ({_path} is "/test"):
        set {_body} to "{""test"":""done""}"
        set {_request}'s response header "Content-Type" to "application/json"
        reply {_request} with body {_body} and response code 200
        exit

    if ({_path} contains "/sendmessage/"):
        set {_context} to {_path}
        replace "/sendmessage/" in {_context} with ""
        set {_context::*} to split {_context} at "/"

        set {_player} to {_context::1} parsed as offline player

        if ({_player} is online):
            send {_context::2} to {_player}
            set {_body} to "{""sent"":true}"
        else:
            set {_body} to "{""sent"":false}"
            
        set {_request}'s response header "Content-Type" to "application/json"
        reply {_request} with body {_body} and response code 200
        exit

    reply {_request} as not found
```

## やることリスト

- POSTリクエストを送れるようにする
- PUTリクエストを送れるようにする
- DELETEリクエストを送れるようにする
- 1.12.2 ~ 1.19.2での動作を確認しそれらのバージョンで動作できるようにする