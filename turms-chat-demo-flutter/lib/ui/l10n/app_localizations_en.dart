import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for English (`en`).
class AppLocalizationsEn extends AppLocalizations {
  AppLocalizationsEn([String locale = 'en']) : super(locale);

  @override
  String get about => 'About';

  @override
  String get accept => 'Accept';

  @override
  String get actionOnClose => 'Action on Close';

  @override
  String get actions => 'Actions';

  @override
  String get addContact => 'Add Contact';

  @override
  String get addNewMember => 'Add New Member';

  @override
  String get addNewRelationship => 'Add New Relationship';

  @override
  String get alreadyLatestVersion => 'This is the latest version';

  @override
  String get alwaysOnTopDisable => 'Disable Always on Top';

  @override
  String get alwaysOnTopEnable => 'Always on Top';

  @override
  String get appearance => 'Appearance';

  @override
  String get audio => 'Audio';

  @override
  String get autoLogin => 'Auto Login';

  @override
  String get brightness => 'Brightness';

  @override
  String get cancel => 'Cancel';

  @override
  String get changingStatusToAwayWhenInactiveForMinutes =>
      'Changing status to \"Away\" when inactive for %% minutes';

  @override
  String get chatHistory => 'Chat History';

  @override
  String get chatInfo => 'Chat Info';

  @override
  String get chats => 'Chats';

  @override
  String get checkForUpdatesAutomatically => 'Check for Updates Automatically';

  @override
  String get clearChatHistory => 'Clear Chat History';

  @override
  String get close => 'Close';

  @override
  String get confirm => 'Confirm';

  @override
  String get contacts => 'Contacts';

  @override
  String get create => 'Create';

  @override
  String get createGroup => 'Create Group';

  @override
  String get darkTheme => 'Dark';

  @override
  String get delete => 'Delete';

  @override
  String get deleteChat => 'Delete Chat';

  @override
  String get disableNewMessageNotification => 'Mute';

  @override
  String get downloadCancel => 'Cancel';

  @override
  String get downloadPause => 'Pause';

  @override
  String get downloadStart => 'Start';

  @override
  String get draft => 'Draft';

  @override
  String get dropFilesHere => 'Drop files here';

  @override
  String get edit => 'Edit';

  @override
  String get editProfileImage => 'Edit Profile Image';

  @override
  String get enableNewMessageNotification => 'Unmute';

  @override
  String get error => 'Error';

  @override
  String get exit => 'Exit';

  @override
  String get failedToDownload => 'Failed to download';

  @override
  String failedToDownloadFileTooLarge(Object size) {
    return 'Failed to download file: File is larger than ${size}MB';
  }

  @override
  String get failedToSendImageInvalidUrl => 'Failed to send image: Invalid URL';

  @override
  String failedToUpdateSettings(Object error) {
    return 'Failed to update settings: $error';
  }

  @override
  String get file => 'File';

  @override
  String get fileName => 'Name';

  @override
  String get fileSize => 'Size';

  @override
  String get fileTransfer => 'File Transfer';

  @override
  String get fileType => 'Type';

  @override
  String get fileUploadDate => 'Upload Date';

  @override
  String get fileUploader => 'Uploader';

  @override
  String get files => 'Files';

  @override
  String get flipHorizontally => 'Flip Horizontally';

  @override
  String get flipVertically => 'Flip Vertically';

  @override
  String get friendRequests => 'Friend Requests';

  @override
  String get goToChatPage => 'Go to Chat Page';

  @override
  String get goToContactsPage => 'Go to Contacts Page';

  @override
  String get goToFilesPage => 'Go to Files Page';

  @override
  String get groupId => 'Group ID';

  @override
  String get groupMembershipRequests => 'Group Membership Requests';

  @override
  String get groups => 'Groups';

  @override
  String get image => 'Image';

  @override
  String get joinGroup => 'Join Group';

  @override
  String get language => 'Language';

  @override
  String get launchAndExit => 'Launch and Exit';

  @override
  String get launchOnStartup => 'Run on Startup';

  @override
  String get leaveGroup => 'Leave Group';

  @override
  String get lightTheme => 'Light';

  @override
  String get loading => 'Loading';

  @override
  String get logOut => 'Log Out';

  @override
  String get login => 'Login';

  @override
  String get lowDiskSpace => 'Low Disk Space';

  @override
  String lowDiskSpacePrompt(Object space) {
    return 'The disk space is lower than ${space}MB. Please delete some files or applications to free up space.';
  }

  @override
  String get maximize => 'Maximize';

  @override
  String get message => 'Message';

  @override
  String get messages => 'Messages';

  @override
  String get minimize => 'Minimize';

  @override
  String get minimizeToTray => 'Minimize to Tray';

  @override
  String get muteNotifications => 'Mute Notifications';

  @override
  String get network => 'Network';

  @override
  String get newMessageNotification => 'New Message Notification';

  @override
  String get noMatchingGroupMembersFound => 'No matching members found';

  @override
  String get noResultsFound => 'No results found';

  @override
  String get none => 'None';

  @override
  String get notifications => 'Notifications';

  @override
  String get openAboutDialog => 'Open About Dialog';

  @override
  String get openFolder => 'Open Folder';

  @override
  String get openSettingsDialog => 'Open Settings Dialog';

  @override
  String get pin => 'Pin';

  @override
  String get pleaseEnterPassword => 'Please enter password';

  @override
  String get pleaseEnterUserId => 'Please enter user ID';

  @override
  String get progress => 'Progress';

  @override
  String relatedMessages(Object count) {
    return '$count related messages';
  }

  @override
  String get rememberMe => 'Remember Me';

  @override
  String get removeAttachment => 'Remove Attachment';

  @override
  String get requestNotification => 'Request Notification';

  @override
  String get reset => 'Reset';

  @override
  String get restore => 'Restore';

  @override
  String get rotateAndFlip => 'Rotate & Flip';

  @override
  String get rotateLeft => 'Rotate Left';

  @override
  String get rotateRight => 'Rotate Right';

  @override
  String get search => 'Search';

  @override
  String get searchStickers => 'Search Stickers';

  @override
  String get selectProfileImage => 'Select Image';

  @override
  String get selectedContacts => 'Selected Contacts';

  @override
  String get send => 'Send';

  @override
  String get sendMessage => 'Send Message';

  @override
  String get settings => 'Settings';

  @override
  String get shortcuts => 'Shortcuts';

  @override
  String get status => 'Status';

  @override
  String get sticker => 'Sticker';

  @override
  String get systemLanguage => 'System';

  @override
  String get systemTheme => 'System';

  @override
  String get theme => 'Theme';

  @override
  String get today => 'Today';

  @override
  String get unpin => 'Unpin';

  @override
  String get update => 'Update';

  @override
  String get userId => 'User ID';

  @override
  String get userPassword => 'Password';

  @override
  String get userPresenceAppearOffline => 'Appear Offline';

  @override
  String get userPresenceAvailable => 'Available';

  @override
  String get userPresenceAway => 'Away';

  @override
  String get userPresenceBusy => 'Busy';

  @override
  String get userPresenceDoNotDisturb => 'Do Not Disturb';

  @override
  String get version => 'Version';

  @override
  String get video => 'Video';

  @override
  String get videoNotFound => 'Video not found';

  @override
  String get yesterday => 'Yesterday';

  @override
  String get youtube => 'YouTube';
}
