// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for Japanese (`ja`).
class AppLocalizationsJa extends AppLocalizations {
  AppLocalizationsJa([String locale = 'ja']) : super(locale);

  @override
  String get about => '概要';

  @override
  String get accept => '受け入れる';

  @override
  String get actionOnClose => '閉じるときのアクション';

  @override
  String get actions => 'アクション';

  @override
  String get addContact => '連絡先を追加';

  @override
  String get addNewMember => '新しいメンバーを追加';

  @override
  String get addNewRelationship => '新しい関係を追加';

  @override
  String get alreadyLatestVersion => 'これが最新バージョンです';

  @override
  String get alwaysOnTopDisable => '常に最前面を無効にする';

  @override
  String get alwaysOnTopEnable => '常に最前面';

  @override
  String get appearance => '外観';

  @override
  String get audio => 'オーディオ';

  @override
  String get autoLogin => '自動ログイン';

  @override
  String get brightness => '明るさ';

  @override
  String get cancel => 'キャンセル';

  @override
  String get changingStatusToAwayWhenInactiveForMinutes =>
      '%% 分間非アクティブのときに「離席」にステータスを変更';

  @override
  String get chatHistory => 'チャット履歴';

  @override
  String get chatInfo => 'チャット情報';

  @override
  String get chats => 'チャット';

  @override
  String get checkForUpdatesAutomatically => '自動的に更新を確認';

  @override
  String get clearChatHistory => 'チャット履歴をクリア';

  @override
  String get close => '閉じる';

  @override
  String get confirm => '確認';

  @override
  String get contacts => '連絡先';

  @override
  String get create => '作成';

  @override
  String get createGroup => 'グループを作成';

  @override
  String get darkTheme => 'ダーク';

  @override
  String get delete => '削除';

  @override
  String get deleteChat => 'チャットを削除';

  @override
  String get disableNewMessageNotification => 'ミュート';

  @override
  String get downloadCancel => 'キャンセル';

  @override
  String get downloadPause => '一時停止';

  @override
  String get downloadStart => '開始';

  @override
  String get draft => '下書き';

  @override
  String get dropFilesHere => 'ここにファイルをドロップ';

  @override
  String get edit => '編集';

  @override
  String get editProfileImage => 'プロフィール画像を編集';

  @override
  String get enableNewMessageNotification => 'ミュート解除';

  @override
  String get error => 'エラー';

  @override
  String get exit => '終了';

  @override
  String get failedToDownload => 'ダウンロードに失敗しました';

  @override
  String failedToDownloadFileTooLarge(Object size) {
    return 'ダウンロードに失敗しました: ファイルが ${size}MB より大きい';
  }

  @override
  String get failedToSendImageInvalidUrl => '画像の送信に失敗しました: 無効なURL';

  @override
  String failedToUpdateSettings(Object error) {
    return '設定の更新に失敗しました: $error';
  }

  @override
  String get file => 'ファイル';

  @override
  String get fileName => '名前';

  @override
  String get fileSize => 'サイズ';

  @override
  String get fileTransfer => 'ファイル転送';

  @override
  String get fileType => 'タイプ';

  @override
  String get fileUploadDate => 'アップロード日';

  @override
  String get fileUploader => 'アップローダー';

  @override
  String get files => 'ファイル';

  @override
  String get flipHorizontally => '水平方向に反転';

  @override
  String get flipVertically => '垂直方向に反転';

  @override
  String get friendRequests => '友達リクエスト';

  @override
  String get goToChatPage => 'チャットページに移動';

  @override
  String get goToContactsPage => '連絡先ページに移動';

  @override
  String get goToFilesPage => 'ファイルページに移動';

  @override
  String get groupId => 'グループID';

  @override
  String get groupMembershipRequests => 'グループメンバーシップリクエスト';

  @override
  String get groups => 'グループ';

  @override
  String get image => '画像';

  @override
  String get joinGroup => 'グループに参加';

  @override
  String get language => '言語';

  @override
  String get launchAndExit => '起動と終了';

  @override
  String get launchOnStartup => '起動時に実行';

  @override
  String get leaveGroup => 'グループを離れる';

  @override
  String get lightTheme => 'ライト';

  @override
  String get loading => '読み込み中';

  @override
  String get logOut => 'ログアウト';

  @override
  String get login => 'ログイン';

  @override
  String get lowDiskSpace => 'ディスクスペースが不足しています';

  @override
  String lowDiskSpacePrompt(Object space) {
    return '${space}MB よりもディスクスペースが少なくなっています。スペースを空けるためにいくつかのファイルやアプリケーションを削除してください。';
  }

  @override
  String get maximize => '最大化';

  @override
  String get message => 'メッセージ';

  @override
  String get messages => 'メッセージ';

  @override
  String get minimize => '最小化';

  @override
  String get minimizeToTray => 'トレイに最小化';

  @override
  String get muteNotifications => '通知をミュート';

  @override
  String get network => 'ネットワーク';

  @override
  String get newMessageNotification => '新しいメッセージ通知';

  @override
  String get noMatchingGroupMembersFound => '一致するメンバーが見つかりませんでした';

  @override
  String get noResultsFound => '結果が見つかりませんでした';

  @override
  String get none => 'なし';

  @override
  String get notifications => '通知';

  @override
  String get openAboutDialog => '概要ダイアログを開く';

  @override
  String get openFolder => 'フォルダーを開く';

  @override
  String get openSettingsDialog => '設定ダイアログを開く';

  @override
  String get pin => 'ピン留め';

  @override
  String get pleaseEnterPassword => 'パスワードを入力してください';

  @override
  String get pleaseEnterUserId => 'ユーザーIDを入力してください';

  @override
  String get progress => '進行状況';

  @override
  String relatedMessages(Object count) {
    return '$count 件の関連メッセージ';
  }

  @override
  String get rememberMe => 'ログイン情報を記憶する';

  @override
  String get removeAttachment => '添付ファイルを削除';

  @override
  String get requestNotification => '通知をリクエスト';

  @override
  String get reset => 'リセット';

  @override
  String get restore => '復元';

  @override
  String get rotateAndFlip => '回転 & 反転';

  @override
  String get rotateLeft => '左に回転';

  @override
  String get rotateRight => '右に回転';

  @override
  String get search => '検索';

  @override
  String get searchStickers => 'ステッカーを検索';

  @override
  String get selectProfileImage => '画像を選択';

  @override
  String get selectedContacts => '選択した連絡先';

  @override
  String get send => '送信';

  @override
  String get sendMessage => 'メッセージを送信';

  @override
  String get settings => '設定';

  @override
  String get shortcuts => 'ショートカット';

  @override
  String get status => 'ステータス';

  @override
  String get sticker => 'ステッカー';

  @override
  String get systemLanguage => 'システム';

  @override
  String get systemTheme => 'システム';

  @override
  String get theme => 'テーマ';

  @override
  String get today => '今日';

  @override
  String get unpin => 'ピン留め解除';

  @override
  String get update => '更新';

  @override
  String get userId => 'ユーザーID';

  @override
  String get userPassword => 'パスワード';

  @override
  String get userPresenceAppearOffline => 'オフライン表示';

  @override
  String get userPresenceAvailable => '使用可能';

  @override
  String get userPresenceAway => '退席中';

  @override
  String get userPresenceBusy => '取り込み中';

  @override
  String get userPresenceDoNotDisturb => '応答不可';

  @override
  String get version => 'バージョン';

  @override
  String get video => 'ビデオ';

  @override
  String get videoNotFound => 'ビデオが見つかりません';

  @override
  String get yesterday => '昨日';

  @override
  String get youtube => 'YouTube';
}
