import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for Chinese (`zh`).
class AppLocalizationsZh extends AppLocalizations {
  AppLocalizationsZh([String locale = 'zh']) : super(locale);

  @override
  String get about => '关于';

  @override
  String get accept => '接受';

  @override
  String get actionOnClose => '关闭时的动作';

  @override
  String get actions => '操作';

  @override
  String get addContact => '添加联系人';

  @override
  String get addNewMember => '添加新成员';

  @override
  String get addNewRelationship => '添加新关系';

  @override
  String get alreadyLatestVersion => '这是最新版本';

  @override
  String get alwaysOnTopDisable => '取消置顶';

  @override
  String get alwaysOnTopEnable => '置顶';

  @override
  String get appearance => '外观';

  @override
  String get audio => '音频';

  @override
  String get autoLogin => '自动登录';

  @override
  String get brightness => '亮度';

  @override
  String get cancel => '取消';

  @override
  String get changingStatusToAwayWhenInactiveForMinutes =>
      '自动将状态设置为离开，当处于非活动状态超过%%分钟';

  @override
  String get chatHistory => '聊天记录';

  @override
  String get chatInfo => '聊天信息';

  @override
  String get chats => '聊天';

  @override
  String get checkForUpdatesAutomatically => '自动检查更新';

  @override
  String get clearChatHistory => '清除聊天记录';

  @override
  String get close => '关闭';

  @override
  String get confirm => '确定';

  @override
  String get contacts => '联系人';

  @override
  String get create => '创建';

  @override
  String get createGroup => '创建群组';

  @override
  String get darkTheme => '暗主题';

  @override
  String get delete => '删除';

  @override
  String get deleteChat => '删除聊天记录';

  @override
  String get disableNewMessageNotification => '禁用新消息通知';

  @override
  String get downloadCancel => '取消下载';

  @override
  String get downloadPause => '暂停下载';

  @override
  String get downloadStart => '开始下载';

  @override
  String get draft => '草稿';

  @override
  String get dropFilesHere => '在此放置文件';

  @override
  String get edit => '编辑';

  @override
  String get editProfileImage => '编辑头像';

  @override
  String get enableNewMessageNotification => '启用新消息通知';

  @override
  String get error => '错误';

  @override
  String get exit => '退出';

  @override
  String get failedToDownload => '下载失败';

  @override
  String failedToDownloadFileTooLarge(Object size) {
    return '文件大于${size}MB，无法下载';
  }

  @override
  String get failedToSendImageInvalidUrl => '发送图片失败: 无效图片URL';

  @override
  String failedToUpdateSettings(Object error) {
    return '设置更新失败: $error';
  }

  @override
  String get file => '文件';

  @override
  String get fileName => '文件名';

  @override
  String get fileSize => '大小';

  @override
  String get fileTransfer => '文件传输助手';

  @override
  String get fileType => '类型';

  @override
  String get fileUploadDate => '上传时间';

  @override
  String get fileUploader => '上传者';

  @override
  String get files => '文件';

  @override
  String get flipHorizontally => '水平翻转';

  @override
  String get flipVertically => '垂直翻转';

  @override
  String get friendRequests => '好友请求';

  @override
  String get goToChatPage => '切换至聊天页面';

  @override
  String get goToContactsPage => '切换至联系人页面';

  @override
  String get goToFilesPage => '切换至文件页面';

  @override
  String get groupId => '群组ID';

  @override
  String get groupMembershipRequests => '入群请求';

  @override
  String get groups => '群组';

  @override
  String get image => '图片';

  @override
  String get joinGroup => '加入群组';

  @override
  String get language => '语言';

  @override
  String get launchAndExit => '启动与退出';

  @override
  String get launchOnStartup => '启动时运行';

  @override
  String get leaveGroup => '离开群组';

  @override
  String get lightTheme => '亮主题';

  @override
  String get loading => '加载中';

  @override
  String get logOut => '退出登陆';

  @override
  String get login => '登陆';

  @override
  String get lowDiskSpace => '存储空间不足';

  @override
  String lowDiskSpacePrompt(Object space) {
    return '存储空间低于${space}MB。请删除一些文件或应用以释放空间。';
  }

  @override
  String get maximize => '最大化';

  @override
  String get message => '消息';

  @override
  String get messages => '发送消息';

  @override
  String get minimize => '最小化';

  @override
  String get minimizeToTray => '最小化到托盘';

  @override
  String get muteNotifications => '消息免打扰';

  @override
  String get network => '网络';

  @override
  String get newMessageNotification => '新消息通知';

  @override
  String get noMatchingGroupMembersFound => '未找到匹配的成员';

  @override
  String get noResultsFound => '没有找到结果';

  @override
  String get none => '无';

  @override
  String get notifications => '通知';

  @override
  String get openAboutDialog => '打开关于对话框';

  @override
  String get openFolder => '打开文件夹';

  @override
  String get openSettingsDialog => '打开设置对话框';

  @override
  String get pin => '置顶';

  @override
  String get pleaseEnterPassword => '请输入密码';

  @override
  String get pleaseEnterUserId => '请输入用户ID';

  @override
  String get progress => '进度';

  @override
  String relatedMessages(Object count) {
    return '$count条相关消息';
  }

  @override
  String get rememberMe => '记住我';

  @override
  String get removeAttachment => '删除附件';

  @override
  String get requestNotification => '请求通知';

  @override
  String get reset => '重置';

  @override
  String get restore => '恢复';

  @override
  String get rotateAndFlip => '旋转 & 翻转';

  @override
  String get rotateLeft => '向左旋转';

  @override
  String get rotateRight => '向右旋转';

  @override
  String get search => '搜索';

  @override
  String get searchStickers => '搜索表情';

  @override
  String get selectProfileImage => '选择头像';

  @override
  String get selectedContacts => '已选中的联系人';

  @override
  String get send => '发送';

  @override
  String get sendMessage => '发送消息';

  @override
  String get settings => '设置';

  @override
  String get shortcuts => '快捷键';

  @override
  String get status => '状态';

  @override
  String get sticker => '表情';

  @override
  String get systemLanguage => '系统语言';

  @override
  String get systemTheme => '系统主题';

  @override
  String get theme => '主题';

  @override
  String get today => '今天';

  @override
  String get unpin => '取消置顶';

  @override
  String get update => '更新';

  @override
  String get userId => '用户ID';

  @override
  String get userPassword => '密码';

  @override
  String get userPresenceAppearOffline => '隐身';

  @override
  String get userPresenceAvailable => '在线';

  @override
  String get userPresenceAway => '离开';

  @override
  String get userPresenceBusy => '忙碌';

  @override
  String get userPresenceDoNotDisturb => '勿扰';

  @override
  String get version => '版本';

  @override
  String get video => '视频';

  @override
  String get videoNotFound => '视频不存在';

  @override
  String get yesterday => '昨天';

  @override
  String get youtube => 'YouTube';
}
