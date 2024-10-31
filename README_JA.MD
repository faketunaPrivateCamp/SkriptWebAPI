# SkriptWebAPI - Skweapi

[[English]](/README.MD) [日本語]

# 免責事項

このアドオンを使用した結果発生したいかなる損害にも責任は一切負いません。全て自己責任でご利用ください。

## 目次

- [SkriptWebAPI - Skweapi](#skriptwebapi---skweapi)
- [免責事項](#免責事項)
  - [目次](#目次)
  - [概要](#概要)
  - [機能](#機能)
  - [インストール](#インストール)
  - [使い方とか](#使い方とか)
    - [重要](#重要)
  - [やることリスト](#やることリスト)


## 概要

Skriptを使用してMinecraftサーバーにHTTPサーバー設置したりとかWeb Request送れるようにします。

## 機能

- HTTPのサーバー建てられます
- Web request送れます

## インストール

Paperサーバーかその派生サーバーでのみ動作します。

Minecraftのバージョン1.13以降のSkript 2.7.3以降でロードできるようにしていますが、1.20.2以降がサポート対象です。

1. [ここ](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases) から `SkriptWebAPI-<バージョン>.jar` をダウンロードしてください。 数字が大きい方が新しいです。
2. ダウンロードしたjarファイルをサーバーのpluginsフォルダに入れてください
3. 起動したら使用可能になります

## 使い方とか

詳しくは以下のサイトから

[![SkriptHubViewTheDocs](http://skripthub.net/static/addon/ViewTheDocsButton.png)](http://skripthub.net/docs/?addon=SkriptWebAPI)


### 重要

イベントの処理の最後ではなく、途中でreplyを使う場合はバグを回避するために `exit` を入れてください。
```sk
if (something):
    reply %httpexchange% with %string% and response code 200
    exit
if (something2):
    reply %httpexchange% with %string% and response code 202
    exit
    
```

また、どの様な理由があれ必ずレスポンスを送信してください。 でなければ、クライアントがサーバーからのレスポンスを待ち続けることになります。
```sk
on receive get request:
    set {_request} to event-request

    if (something):
        reply {_request} with %string% and response code 200
        exit
    
    reply {_request} with %string% and response code 500
```

## やることリスト

- 無し