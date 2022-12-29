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

詳しくは以下のサイトから

[![SkriptHubViewTheDocs](http://skripthub.net/static/addon/ViewTheDocsButton.png)](http://skripthub.net/docs/?addon=SkriptWebAPI)


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

## やることリスト

- POSTリクエストを送れるようにする
- PUTリクエストを送れるようにする
- DELETEリクエストを送れるようにする
- 1.12.2 ~ 1.19.2での動作を確認しそれらのバージョンで動作できるようにする