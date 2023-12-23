# SkriptWebAPI - SKeb

[English readme available](/README.MD)

# Notice

## 現在このアドオンは書き直し/リファクタリングの最中です

# 免責事項

このアドオンを使用した結果発生したいかなる損害にも責任は一切負いません。

## 目次

- [SkriptWebAPI - SKeb](#skriptwebapi---skeb)
- [免責事項](#免責事項)
  - [目次](#目次)
  - [概要](#概要)
  - [機能](#機能)
  - [インストール](#インストール)
  - [使い方とか](#使い方とか)
    - [重要](#重要)
  - [やることリスト](#やることリスト)


## 概要

Skriptを使用してMinecraftサーバーにAPIサーバー設置したりとかWeb Request送れるようにします。

## 機能

- HTTPのAPIサーバー建てられます
- Web request送れます

## インストール

Paperサーバーかその派生サーバーでのみ動作します。

Minecraftのバージョン1.13以降のSkript 2.7.3以降でロードできるようにしていますが、1.20.2以降がサポート対象です。

1. [ここ](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases) から `SkriptWebAPI-<バージョン>.jar` をダウンロードしてください。 数字が大きい方が新しいです。
2. ダウンロードしたjarファイルをサーバーのpluginsフォルダに入れてください
3. 起動したら使用可能になります

## 使い方とか (現在のブランチとは同期していません)

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

- コード全体の最適化/書き直しを完了する