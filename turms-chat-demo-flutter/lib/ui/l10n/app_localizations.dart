import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:intl/intl.dart' as intl;

import 'app_localizations_en.dart';
import 'app_localizations_ja.dart';
import 'app_localizations_zh.dart';

// ignore_for_file: type=lint

/// Callers can lookup localized strings with an instance of AppLocalizations
/// returned by `AppLocalizations.of(context)`.
///
/// Applications need to include `AppLocalizations.delegate()` in their app's
/// `localizationDelegates` list, and the locales they support in the app's
/// `supportedLocales` list. For example:
///
/// ```dart
/// import 'l10n/app_localizations.dart';
///
/// return MaterialApp(
///   localizationsDelegates: AppLocalizations.localizationsDelegates,
///   supportedLocales: AppLocalizations.supportedLocales,
///   home: MyApplicationHome(),
/// );
/// ```
///
/// ## Update pubspec.yaml
///
/// Please make sure to update your pubspec.yaml to include the following
/// packages:
///
/// ```yaml
/// dependencies:
///   # Internationalization support.
///   flutter_localizations:
///     sdk: flutter
///   intl: any # Use the pinned version from flutter_localizations
///
///   # Rest of dependencies
/// ```
///
/// ## iOS Applications
///
/// iOS applications define key application metadata, including supported
/// locales, in an Info.plist file that is built into the application bundle.
/// To configure the locales supported by your app, you’ll need to edit this
/// file.
///
/// First, open your project’s ios/Runner.xcworkspace Xcode workspace file.
/// Then, in the Project Navigator, open the Info.plist file under the Runner
/// project’s Runner folder.
///
/// Next, select the Information Property List item, select Add Item from the
/// Editor menu, then select Localizations from the pop-up menu.
///
/// Select and expand the newly-created Localizations item then, for each
/// locale your application supports, add a new item and select the locale
/// you wish to add from the pop-up menu in the Value field. This list should
/// be consistent with the languages listed in the AppLocalizations.supportedLocales
/// property.
abstract class AppLocalizations {
  AppLocalizations(String locale)
      : localeName = intl.Intl.canonicalizedLocale(locale.toString());

  final String localeName;

  static AppLocalizations of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations)!;
  }

  static const LocalizationsDelegate<AppLocalizations> delegate =
      _AppLocalizationsDelegate();

  /// A list of this localizations delegate along with the default localizations
  /// delegates.
  ///
  /// Returns a list of localizations delegates containing this delegate along with
  /// GlobalMaterialLocalizations.delegate, GlobalCupertinoLocalizations.delegate,
  /// and GlobalWidgetsLocalizations.delegate.
  ///
  /// Additional delegates can be added by appending to this list in
  /// MaterialApp. This list does not have to be used at all if a custom list
  /// of delegates is preferred or required.
  static const List<LocalizationsDelegate<dynamic>> localizationsDelegates =
      <LocalizationsDelegate<dynamic>>[
    delegate,
    GlobalMaterialLocalizations.delegate,
    GlobalCupertinoLocalizations.delegate,
    GlobalWidgetsLocalizations.delegate,
  ];

  /// A list of this localizations delegate's supported locales.
  static const List<Locale> supportedLocales = <Locale>[
    Locale('en'),
    Locale('ja'),
    Locale('zh')
  ];

  /// No description provided for @about.
  ///
  /// In en, this message translates to:
  /// **'About'**
  String get about;

  /// No description provided for @accept.
  ///
  /// In en, this message translates to:
  /// **'Accept'**
  String get accept;

  /// No description provided for @actionOnClose.
  ///
  /// In en, this message translates to:
  /// **'Action on Close'**
  String get actionOnClose;

  /// No description provided for @actions.
  ///
  /// In en, this message translates to:
  /// **'Actions'**
  String get actions;

  /// No description provided for @addContact.
  ///
  /// In en, this message translates to:
  /// **'Add Contact'**
  String get addContact;

  /// No description provided for @addNewMember.
  ///
  /// In en, this message translates to:
  /// **'Add New Member'**
  String get addNewMember;

  /// No description provided for @addNewRelationship.
  ///
  /// In en, this message translates to:
  /// **'Add New Relationship'**
  String get addNewRelationship;

  /// No description provided for @alreadyLatestVersion.
  ///
  /// In en, this message translates to:
  /// **'This is the latest version'**
  String get alreadyLatestVersion;

  /// No description provided for @alwaysOnTopDisable.
  ///
  /// In en, this message translates to:
  /// **'Disable Always on Top'**
  String get alwaysOnTopDisable;

  /// No description provided for @alwaysOnTopEnable.
  ///
  /// In en, this message translates to:
  /// **'Always on Top'**
  String get alwaysOnTopEnable;

  /// No description provided for @appearance.
  ///
  /// In en, this message translates to:
  /// **'Appearance'**
  String get appearance;

  /// No description provided for @audio.
  ///
  /// In en, this message translates to:
  /// **'Audio'**
  String get audio;

  /// No description provided for @autoLogin.
  ///
  /// In en, this message translates to:
  /// **'Auto Login'**
  String get autoLogin;

  /// No description provided for @brightness.
  ///
  /// In en, this message translates to:
  /// **'Brightness'**
  String get brightness;

  /// No description provided for @cancel.
  ///
  /// In en, this message translates to:
  /// **'Cancel'**
  String get cancel;

  /// No description provided for @changingStatusToAwayWhenInactiveForMinutes.
  ///
  /// In en, this message translates to:
  /// **'Changing status to \"Away\" when inactive for %% minutes'**
  String get changingStatusToAwayWhenInactiveForMinutes;

  /// No description provided for @chatHistory.
  ///
  /// In en, this message translates to:
  /// **'Chat History'**
  String get chatHistory;

  /// No description provided for @chatInfo.
  ///
  /// In en, this message translates to:
  /// **'Chat Info'**
  String get chatInfo;

  /// No description provided for @chats.
  ///
  /// In en, this message translates to:
  /// **'Chats'**
  String get chats;

  /// No description provided for @checkForUpdatesAutomatically.
  ///
  /// In en, this message translates to:
  /// **'Check for Updates Automatically'**
  String get checkForUpdatesAutomatically;

  /// No description provided for @clearChatHistory.
  ///
  /// In en, this message translates to:
  /// **'Clear Chat History'**
  String get clearChatHistory;

  /// No description provided for @close.
  ///
  /// In en, this message translates to:
  /// **'Close'**
  String get close;

  /// No description provided for @confirm.
  ///
  /// In en, this message translates to:
  /// **'Confirm'**
  String get confirm;

  /// No description provided for @contacts.
  ///
  /// In en, this message translates to:
  /// **'Contacts'**
  String get contacts;

  /// No description provided for @create.
  ///
  /// In en, this message translates to:
  /// **'Create'**
  String get create;

  /// No description provided for @createGroup.
  ///
  /// In en, this message translates to:
  /// **'Create Group'**
  String get createGroup;

  /// No description provided for @darkTheme.
  ///
  /// In en, this message translates to:
  /// **'Dark'**
  String get darkTheme;

  /// No description provided for @delete.
  ///
  /// In en, this message translates to:
  /// **'Delete'**
  String get delete;

  /// No description provided for @deleteChat.
  ///
  /// In en, this message translates to:
  /// **'Delete Chat'**
  String get deleteChat;

  /// No description provided for @disableNewMessageNotification.
  ///
  /// In en, this message translates to:
  /// **'Mute'**
  String get disableNewMessageNotification;

  /// No description provided for @downloadCancel.
  ///
  /// In en, this message translates to:
  /// **'Cancel'**
  String get downloadCancel;

  /// No description provided for @downloadPause.
  ///
  /// In en, this message translates to:
  /// **'Pause'**
  String get downloadPause;

  /// No description provided for @downloadStart.
  ///
  /// In en, this message translates to:
  /// **'Start'**
  String get downloadStart;

  /// No description provided for @draft.
  ///
  /// In en, this message translates to:
  /// **'Draft'**
  String get draft;

  /// No description provided for @dropFilesHere.
  ///
  /// In en, this message translates to:
  /// **'Drop files here'**
  String get dropFilesHere;

  /// No description provided for @edit.
  ///
  /// In en, this message translates to:
  /// **'Edit'**
  String get edit;

  /// No description provided for @editProfileImage.
  ///
  /// In en, this message translates to:
  /// **'Edit Profile Image'**
  String get editProfileImage;

  /// No description provided for @enableNewMessageNotification.
  ///
  /// In en, this message translates to:
  /// **'Unmute'**
  String get enableNewMessageNotification;

  /// No description provided for @error.
  ///
  /// In en, this message translates to:
  /// **'Error'**
  String get error;

  /// No description provided for @exit.
  ///
  /// In en, this message translates to:
  /// **'Exit'**
  String get exit;

  /// No description provided for @failedToDownload.
  ///
  /// In en, this message translates to:
  /// **'Failed to download'**
  String get failedToDownload;

  /// No description provided for @failedToDownloadFileTooLarge.
  ///
  /// In en, this message translates to:
  /// **'Failed to download file: File is larger than {size}MB'**
  String failedToDownloadFileTooLarge(Object size);

  /// No description provided for @failedToSendImageInvalidUrl.
  ///
  /// In en, this message translates to:
  /// **'Failed to send image: Invalid URL'**
  String get failedToSendImageInvalidUrl;

  /// No description provided for @failedToUpdateSettings.
  ///
  /// In en, this message translates to:
  /// **'Failed to update settings: {error}'**
  String failedToUpdateSettings(Object error);

  /// No description provided for @file.
  ///
  /// In en, this message translates to:
  /// **'File'**
  String get file;

  /// No description provided for @fileName.
  ///
  /// In en, this message translates to:
  /// **'Name'**
  String get fileName;

  /// No description provided for @fileSize.
  ///
  /// In en, this message translates to:
  /// **'Size'**
  String get fileSize;

  /// No description provided for @fileTransfer.
  ///
  /// In en, this message translates to:
  /// **'File Transfer'**
  String get fileTransfer;

  /// No description provided for @fileType.
  ///
  /// In en, this message translates to:
  /// **'Type'**
  String get fileType;

  /// No description provided for @fileUploadDate.
  ///
  /// In en, this message translates to:
  /// **'Upload Date'**
  String get fileUploadDate;

  /// No description provided for @fileUploader.
  ///
  /// In en, this message translates to:
  /// **'Uploader'**
  String get fileUploader;

  /// No description provided for @files.
  ///
  /// In en, this message translates to:
  /// **'Files'**
  String get files;

  /// No description provided for @flipHorizontally.
  ///
  /// In en, this message translates to:
  /// **'Flip Horizontally'**
  String get flipHorizontally;

  /// No description provided for @flipVertically.
  ///
  /// In en, this message translates to:
  /// **'Flip Vertically'**
  String get flipVertically;

  /// No description provided for @friendRequests.
  ///
  /// In en, this message translates to:
  /// **'Friend Requests'**
  String get friendRequests;

  /// No description provided for @goToChatPage.
  ///
  /// In en, this message translates to:
  /// **'Go to Chat Page'**
  String get goToChatPage;

  /// No description provided for @goToContactsPage.
  ///
  /// In en, this message translates to:
  /// **'Go to Contacts Page'**
  String get goToContactsPage;

  /// No description provided for @goToFilesPage.
  ///
  /// In en, this message translates to:
  /// **'Go to Files Page'**
  String get goToFilesPage;

  /// No description provided for @groupId.
  ///
  /// In en, this message translates to:
  /// **'Group ID'**
  String get groupId;

  /// No description provided for @groupMembershipRequests.
  ///
  /// In en, this message translates to:
  /// **'Group Membership Requests'**
  String get groupMembershipRequests;

  /// No description provided for @groups.
  ///
  /// In en, this message translates to:
  /// **'Groups'**
  String get groups;

  /// No description provided for @image.
  ///
  /// In en, this message translates to:
  /// **'Image'**
  String get image;

  /// No description provided for @joinGroup.
  ///
  /// In en, this message translates to:
  /// **'Join Group'**
  String get joinGroup;

  /// No description provided for @language.
  ///
  /// In en, this message translates to:
  /// **'Language'**
  String get language;

  /// No description provided for @launchAndExit.
  ///
  /// In en, this message translates to:
  /// **'Launch and Exit'**
  String get launchAndExit;

  /// No description provided for @launchOnStartup.
  ///
  /// In en, this message translates to:
  /// **'Run on Startup'**
  String get launchOnStartup;

  /// No description provided for @leaveGroup.
  ///
  /// In en, this message translates to:
  /// **'Leave Group'**
  String get leaveGroup;

  /// No description provided for @lightTheme.
  ///
  /// In en, this message translates to:
  /// **'Light'**
  String get lightTheme;

  /// No description provided for @loading.
  ///
  /// In en, this message translates to:
  /// **'Loading'**
  String get loading;

  /// No description provided for @logOut.
  ///
  /// In en, this message translates to:
  /// **'Log Out'**
  String get logOut;

  /// No description provided for @login.
  ///
  /// In en, this message translates to:
  /// **'Login'**
  String get login;

  /// No description provided for @lowDiskSpace.
  ///
  /// In en, this message translates to:
  /// **'Low Disk Space'**
  String get lowDiskSpace;

  /// No description provided for @lowDiskSpacePrompt.
  ///
  /// In en, this message translates to:
  /// **'The disk space is lower than {space}MB. Please delete some files or applications to free up space.'**
  String lowDiskSpacePrompt(Object space);

  /// No description provided for @maximize.
  ///
  /// In en, this message translates to:
  /// **'Maximize'**
  String get maximize;

  /// No description provided for @message.
  ///
  /// In en, this message translates to:
  /// **'Message'**
  String get message;

  /// No description provided for @messages.
  ///
  /// In en, this message translates to:
  /// **'Messages'**
  String get messages;

  /// No description provided for @minimize.
  ///
  /// In en, this message translates to:
  /// **'Minimize'**
  String get minimize;

  /// No description provided for @minimizeToTray.
  ///
  /// In en, this message translates to:
  /// **'Minimize to Tray'**
  String get minimizeToTray;

  /// No description provided for @muteNotifications.
  ///
  /// In en, this message translates to:
  /// **'Mute Notifications'**
  String get muteNotifications;

  /// No description provided for @network.
  ///
  /// In en, this message translates to:
  /// **'Network'**
  String get network;

  /// No description provided for @newMessageNotification.
  ///
  /// In en, this message translates to:
  /// **'New Message Notification'**
  String get newMessageNotification;

  /// No description provided for @noMatchingGroupMembersFound.
  ///
  /// In en, this message translates to:
  /// **'No matching members found'**
  String get noMatchingGroupMembersFound;

  /// No description provided for @noResultsFound.
  ///
  /// In en, this message translates to:
  /// **'No results found'**
  String get noResultsFound;

  /// No description provided for @none.
  ///
  /// In en, this message translates to:
  /// **'None'**
  String get none;

  /// No description provided for @notifications.
  ///
  /// In en, this message translates to:
  /// **'Notifications'**
  String get notifications;

  /// No description provided for @openAboutDialog.
  ///
  /// In en, this message translates to:
  /// **'Open About Dialog'**
  String get openAboutDialog;

  /// No description provided for @openFolder.
  ///
  /// In en, this message translates to:
  /// **'Open Folder'**
  String get openFolder;

  /// No description provided for @openSettingsDialog.
  ///
  /// In en, this message translates to:
  /// **'Open Settings Dialog'**
  String get openSettingsDialog;

  /// No description provided for @pin.
  ///
  /// In en, this message translates to:
  /// **'Pin'**
  String get pin;

  /// No description provided for @pleaseEnterPassword.
  ///
  /// In en, this message translates to:
  /// **'Please enter password'**
  String get pleaseEnterPassword;

  /// No description provided for @pleaseEnterUserId.
  ///
  /// In en, this message translates to:
  /// **'Please enter user ID'**
  String get pleaseEnterUserId;

  /// No description provided for @progress.
  ///
  /// In en, this message translates to:
  /// **'Progress'**
  String get progress;

  /// No description provided for @relatedMessages.
  ///
  /// In en, this message translates to:
  /// **'{count} related messages'**
  String relatedMessages(Object count);

  /// No description provided for @rememberMe.
  ///
  /// In en, this message translates to:
  /// **'Remember Me'**
  String get rememberMe;

  /// No description provided for @removeAttachment.
  ///
  /// In en, this message translates to:
  /// **'Remove Attachment'**
  String get removeAttachment;

  /// No description provided for @requestNotification.
  ///
  /// In en, this message translates to:
  /// **'Request Notification'**
  String get requestNotification;

  /// No description provided for @reset.
  ///
  /// In en, this message translates to:
  /// **'Reset'**
  String get reset;

  /// No description provided for @restore.
  ///
  /// In en, this message translates to:
  /// **'Restore'**
  String get restore;

  /// No description provided for @rotateAndFlip.
  ///
  /// In en, this message translates to:
  /// **'Rotate & Flip'**
  String get rotateAndFlip;

  /// No description provided for @rotateLeft.
  ///
  /// In en, this message translates to:
  /// **'Rotate Left'**
  String get rotateLeft;

  /// No description provided for @rotateRight.
  ///
  /// In en, this message translates to:
  /// **'Rotate Right'**
  String get rotateRight;

  /// No description provided for @search.
  ///
  /// In en, this message translates to:
  /// **'Search'**
  String get search;

  /// No description provided for @searchStickers.
  ///
  /// In en, this message translates to:
  /// **'Search Stickers'**
  String get searchStickers;

  /// No description provided for @selectProfileImage.
  ///
  /// In en, this message translates to:
  /// **'Select Image'**
  String get selectProfileImage;

  /// No description provided for @selectedContacts.
  ///
  /// In en, this message translates to:
  /// **'Selected Contacts'**
  String get selectedContacts;

  /// No description provided for @send.
  ///
  /// In en, this message translates to:
  /// **'Send'**
  String get send;

  /// No description provided for @sendMessage.
  ///
  /// In en, this message translates to:
  /// **'Send Message'**
  String get sendMessage;

  /// No description provided for @settings.
  ///
  /// In en, this message translates to:
  /// **'Settings'**
  String get settings;

  /// No description provided for @shortcuts.
  ///
  /// In en, this message translates to:
  /// **'Shortcuts'**
  String get shortcuts;

  /// No description provided for @status.
  ///
  /// In en, this message translates to:
  /// **'Status'**
  String get status;

  /// No description provided for @sticker.
  ///
  /// In en, this message translates to:
  /// **'Sticker'**
  String get sticker;

  /// No description provided for @systemLanguage.
  ///
  /// In en, this message translates to:
  /// **'System'**
  String get systemLanguage;

  /// No description provided for @systemTheme.
  ///
  /// In en, this message translates to:
  /// **'System'**
  String get systemTheme;

  /// No description provided for @theme.
  ///
  /// In en, this message translates to:
  /// **'Theme'**
  String get theme;

  /// No description provided for @today.
  ///
  /// In en, this message translates to:
  /// **'Today'**
  String get today;

  /// No description provided for @unpin.
  ///
  /// In en, this message translates to:
  /// **'Unpin'**
  String get unpin;

  /// No description provided for @update.
  ///
  /// In en, this message translates to:
  /// **'Update'**
  String get update;

  /// No description provided for @userId.
  ///
  /// In en, this message translates to:
  /// **'User ID'**
  String get userId;

  /// No description provided for @userPassword.
  ///
  /// In en, this message translates to:
  /// **'Password'**
  String get userPassword;

  /// No description provided for @userPresenceAppearOffline.
  ///
  /// In en, this message translates to:
  /// **'Appear Offline'**
  String get userPresenceAppearOffline;

  /// No description provided for @userPresenceAvailable.
  ///
  /// In en, this message translates to:
  /// **'Available'**
  String get userPresenceAvailable;

  /// No description provided for @userPresenceAway.
  ///
  /// In en, this message translates to:
  /// **'Away'**
  String get userPresenceAway;

  /// No description provided for @userPresenceBusy.
  ///
  /// In en, this message translates to:
  /// **'Busy'**
  String get userPresenceBusy;

  /// No description provided for @userPresenceDoNotDisturb.
  ///
  /// In en, this message translates to:
  /// **'Do Not Disturb'**
  String get userPresenceDoNotDisturb;

  /// No description provided for @version.
  ///
  /// In en, this message translates to:
  /// **'Version'**
  String get version;

  /// No description provided for @video.
  ///
  /// In en, this message translates to:
  /// **'Video'**
  String get video;

  /// No description provided for @videoNotFound.
  ///
  /// In en, this message translates to:
  /// **'Video not found'**
  String get videoNotFound;

  /// No description provided for @yesterday.
  ///
  /// In en, this message translates to:
  /// **'Yesterday'**
  String get yesterday;

  /// No description provided for @youtube.
  ///
  /// In en, this message translates to:
  /// **'YouTube'**
  String get youtube;
}

class _AppLocalizationsDelegate
    extends LocalizationsDelegate<AppLocalizations> {
  const _AppLocalizationsDelegate();

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(lookupAppLocalizations(locale));
  }

  @override
  bool isSupported(Locale locale) =>
      <String>['en', 'ja', 'zh'].contains(locale.languageCode);

  @override
  bool shouldReload(_AppLocalizationsDelegate old) => false;
}

AppLocalizations lookupAppLocalizations(Locale locale) {
  // Lookup logic when only language code is specified.
  switch (locale.languageCode) {
    case 'en':
      return AppLocalizationsEn();
    case 'ja':
      return AppLocalizationsJa();
    case 'zh':
      return AppLocalizationsZh();
  }

  throw FlutterError(
      'AppLocalizations.delegate failed to load unsupported locale "$locale". This is likely '
      'an issue with the localizations generation tool. Please file an issue '
      'on GitHub with a reproducible sample app and the gen-l10n configuration '
      'that was used.');
}
