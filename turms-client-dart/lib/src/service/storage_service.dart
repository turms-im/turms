import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';
import 'package:http/http.dart' as http;
import 'package:http_parser/http_parser.dart';

import '../../turms_client.dart';

typedef ResourceUrlExtractor = String Function(Map<String, String>);

class StorageService {
  static const String _resourceIdKeyName = 'id';
  static const String _defaultUrlKeyName = 'url';
  static final Map<StorageResourceType, String> _resourceTypeToBucketName = {
    for (final type in StorageResourceType.values)
      type: type.name.toLowerCase().replaceAll('_', '-')
  };

  final TurmsClient _turmsClient;
  final String serverUrl;

  // TODO: destroy
  final http.Client _httpClient = http.Client();

  StorageService(this._turmsClient, String? storageServerUrl)
      : serverUrl = storageServerUrl == null
            ? 'http://localhost:9000'
            : Uri.parse(storageServerUrl).origin;

  // User profile picture

  Future<Response<StorageUploadResult>> uploadUserProfilePicture(Uint8List data,
      {String? name,
      String? mediaType,
      Map<String, String>? extra,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of user profile picture must not be empty');
    }
    final type = mediaType == null ? null : _parseMediaType(mediaType);
    final uploadInfo = await queryUserProfilePictureUploadInfo(
        name: name, mediaType: mediaType, extra: extra);
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    final id = uploadInfo.data.remove(_resourceIdKeyName);
    if (id == null) {
      throw ResponseException(
          code: ResponseStatusCode.dataNotFound,
          reason:
              'Could not get the resource ID because the key "$_resourceIdKeyName" does not exist in the data: ${uploadInfo.data}');
    }
    return _upload(url, uploadInfo.data, data, id, name: name, mediaType: type);
  }

  Future<Response<void>> deleteUserProfilePicture(
          {Map<String, String>? extra}) =>
      _deleteResource(StorageResourceType.USER_PROFILE_PICTURE, extra: extra);

  Future<Response<StorageResource>> queryUserProfilePicture(Int64 userId,
      {Map<String, String>? extra,
      bool fetchDownloadInfo = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    final downloadInfo = await queryUserProfilePictureDownloadInfo(userId,
        extra: extra, fetch: fetchDownloadInfo, urlKeyName: urlKeyName);
    final url = _getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>> queryUserProfilePictureUploadInfo(
      {String? name, String? mediaType, Map<String, String>? extra}) async {
    return _queryResourceUploadInfo(StorageResourceType.USER_PROFILE_PICTURE,
        name: name, mediaType: mediaType, extra: extra);
  }

  Future<Response<Map<String, String>>> queryUserProfilePictureDownloadInfo(
      Int64 userId,
      {Map<String, String>? extra,
      bool fetch = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (fetch) {
      return _queryResourceDownloadInfo(
          StorageResourceType.USER_PROFILE_PICTURE,
          idNum: userId,
          extra: extra);
    }
    return Response.value({
      urlKeyName:
          '$serverUrl/${_getBucketName(StorageResourceType.USER_PROFILE_PICTURE)}/$userId'
    });
  }

  // Group profile picture

  Future<Response<StorageUploadResult>> uploadGroupProfilePicture(
      Int64 groupId, Uint8List data,
      {String? name,
      String? mediaType,
      Map<String, String>? extra,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of group profile picture must not be empty');
    }
    final type = mediaType == null ? null : _parseMediaType(mediaType);
    final uploadInfo = await queryGroupProfilePictureUploadInfo(groupId,
        name: name, mediaType: mediaType, extra: extra);
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    final id = uploadInfo.data.remove(_resourceIdKeyName);
    if (id == null) {
      throw ResponseException(
          code: ResponseStatusCode.dataNotFound,
          reason:
              'Could not get the resource ID because the key "$_resourceIdKeyName" does not exist in the data: ${uploadInfo.data}');
    }
    return _upload(url, uploadInfo.data, data, id, name: name, mediaType: type);
  }

  Future<Response<void>> deleteGroupProfilePicture(Int64 groupId,
          {Map<String, String>? extra}) =>
      _deleteResource(StorageResourceType.GROUP_PROFILE_PICTURE,
          idNum: groupId, extra: extra);

  Future<Response<StorageResource>> queryGroupProfilePicture(Int64 groupId,
      {Map<String, String>? extra,
      bool fetchDownloadInfo = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    final downloadInfo = await queryGroupProfilePictureDownloadInfo(groupId,
        extra: extra, fetch: fetchDownloadInfo, urlKeyName: urlKeyName);
    final url = _getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>> queryGroupProfilePictureUploadInfo(
          Int64 groupId,
          {String? name,
          String? mediaType,
          Map<String, String>? extra}) =>
      _queryResourceUploadInfo(StorageResourceType.GROUP_PROFILE_PICTURE,
          idNum: groupId, name: name, mediaType: mediaType, extra: extra);

  Future<Response<Map<String, String>>> queryGroupProfilePictureDownloadInfo(
      Int64 groupId,
      {Map<String, String>? extra,
      bool fetch = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (fetch) {
      return _queryResourceDownloadInfo(
          StorageResourceType.GROUP_PROFILE_PICTURE,
          idNum: groupId,
          extra: extra);
    }
    return Response.value({
      urlKeyName:
          '$serverUrl/${_getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/$groupId'
    });
  }

  // Message attachment

  Future<Response<StorageUploadResult>> uploadMessageAttachment(Uint8List data,
          {String? name,
          String? mediaType,
          Map<String, String>? extra,
          String urlKeyName = _defaultUrlKeyName}) async =>
      _uploadMessageAttachment(data,
          name: name,
          mediaType: mediaType,
          extra: extra,
          urlKeyName: urlKeyName);

  Future<Response<StorageUploadResult>>
      uploadMessageAttachmentInPrivateConversation(Int64 userId, Uint8List data,
              {String? name,
              String? mediaType,
              Map<String, String>? extra,
              String urlKeyName = _defaultUrlKeyName}) async =>
          _uploadMessageAttachment(data,
              userId: userId,
              name: name,
              mediaType: mediaType,
              extra: extra,
              urlKeyName: urlKeyName);

  Future<Response<StorageUploadResult>>
      uploadMessageAttachmentInGroupConversation(Int64 groupId, Uint8List data,
              {String? name,
              String? mediaType,
              Map<String, String>? extra,
              String urlKeyName = _defaultUrlKeyName}) async =>
          _uploadMessageAttachment(data,
              groupId: groupId,
              name: name,
              mediaType: mediaType,
              extra: extra,
              urlKeyName: urlKeyName);

  Future<Response<StorageUploadResult>> _uploadMessageAttachment(Uint8List data,
      {Int64? userId,
      Int64? groupId,
      String? name,
      String? mediaType,
      Map<String, String>? extra,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of message attachment must not be empty');
    }
    final type = mediaType == null ? null : _parseMediaType(mediaType);
    final Response<Map<String, String>> uploadInfo;
    if (userId == null && groupId == null) {
      uploadInfo = await queryMessageAttachmentUploadInfo(
          name: name, mediaType: mediaType, extra: extra);
    } else if (userId != null) {
      if (groupId != null) {
        throw ResponseException(
            code: ResponseStatusCode.illegalArgument,
            reason: 'The user ID and the group ID must not both be non-null');
      }
      uploadInfo = await queryMessageAttachmentUploadInfoInPrivateConversation(
          userId,
          name: name,
          mediaType: mediaType,
          extra: extra);
    } else {
      uploadInfo = await queryMessageAttachmentUploadInfoInGroupConversation(
          groupId!,
          name: name,
          mediaType: mediaType,
          extra: extra);
    }
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    final id = uploadInfo.data.remove(_resourceIdKeyName);
    if (id == null) {
      throw ResponseException(
          code: ResponseStatusCode.dataNotFound,
          reason:
              'Could not get the resource ID because the key "$_resourceIdKeyName" does not exist in the data: ${uploadInfo.data}');
    }
    return _upload(url, uploadInfo.data, data, id, name: name, mediaType: type);
  }

  Future<Response<void>> deleteMessageAttachment(
      {Int64? attachmentIdNum,
      String? attachmentIdStr,
      Map<String, String>? extra}) {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    return _deleteResource(StorageResourceType.MESSAGE_ATTACHMENT,
        idNum: attachmentIdNum, idStr: attachmentIdStr, extra: extra);
  }

  Future<Response<void>> shareMessageAttachmentWithUser(Int64 userId,
      {Int64? attachmentIdNum, String? attachmentIdStr}) async {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    final n = await _turmsClient.driver.send(UpdateMessageAttachmentInfoRequest(
        attachmentIdNum: attachmentIdNum,
        attachmentIdStr: attachmentIdStr,
        userIdToShareWith: userId));
    return n.toNullResponse();
  }

  Future<Response<void>> shareMessageAttachmentWithGroup(Int64 groupId,
      {Int64? attachmentIdNum, String? attachmentIdStr}) async {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    final n = await _turmsClient.driver.send(UpdateMessageAttachmentInfoRequest(
        attachmentIdNum: attachmentIdNum,
        attachmentIdStr: attachmentIdStr,
        groupIdToShareWith: groupId));
    return n.toNullResponse();
  }

  Future<Response<void>> unshareMessageAttachmentWithUser(Int64 userId,
      {Int64? attachmentIdNum, String? attachmentIdStr}) async {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    final n = await _turmsClient.driver.send(UpdateMessageAttachmentInfoRequest(
        attachmentIdNum: attachmentIdNum,
        attachmentIdStr: attachmentIdStr,
        userIdToUnshareWith: userId));
    return n.toNullResponse();
  }

  Future<Response<void>> unshareMessageAttachmentWithGroup(Int64 groupId,
      {Int64? attachmentIdNum, String? attachmentIdStr}) async {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    final n = await _turmsClient.driver.send(UpdateMessageAttachmentInfoRequest(
        attachmentIdNum: attachmentIdNum,
        attachmentIdStr: attachmentIdStr,
        groupIdToUnshareWith: groupId));
    return n.toNullResponse();
  }

  Future<Response<StorageResource>> queryMessageAttachment(
      {Int64? attachmentIdNum,
      String? attachmentIdStr,
      Map<String, String>? extra,
      bool fetchDownloadInfo = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    final response = await queryMessageAttachmentDownloadInfo(
        attachmentIdNum: attachmentIdNum,
        attachmentIdStr: attachmentIdStr,
        extra: extra,
        fetch: fetchDownloadInfo,
        urlKeyName: urlKeyName);
    final url = _getAndRemoveResourceUrl(response.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>> queryMessageAttachmentUploadInfo(
          {String? name, String? mediaType, Map<String, String>? extra}) =>
      _queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
          name: name, mediaType: mediaType, extra: extra);

  Future<Response<Map<String, String>>>
      queryMessageAttachmentUploadInfoInPrivateConversation(Int64 userId,
              {String? name, String? mediaType, Map<String, String>? extra}) =>
          _queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
              idNum: userId, name: name, mediaType: mediaType, extra: extra);

  Future<Response<Map<String, String>>>
      queryMessageAttachmentUploadInfoInGroupConversation(Int64 groupId,
              {String? name, String? mediaType, Map<String, String>? extra}) =>
          _queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
              idNum: -groupId, name: name, mediaType: mediaType, extra: extra);

  Future<Response<Map<String, String>>> queryMessageAttachmentDownloadInfo(
      {Int64? attachmentIdNum,
      String? attachmentIdStr,
      Map<String, String>? extra,
      bool fetch = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    if ([attachmentIdNum, attachmentIdStr].areAllNullOrNonNull) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'One and only one attachment ID must be specified');
    }
    if (fetch) {
      return _queryResourceDownloadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
          idNum: attachmentIdNum, idStr: attachmentIdStr, extra: extra);
    }
    return Response.value({
      urlKeyName:
          '$serverUrl/${_getBucketName(StorageResourceType.MESSAGE_ATTACHMENT)}/${attachmentIdNum ?? attachmentIdStr} '
    });
  }

  Future<Response<List<StorageResourceInfo>>>
      queryMessageAttachmentInfosUploadedByMe(
          {DateTime? creationDateStart, DateTime? creationDateEnd}) async {
    final n = await _turmsClient.driver.send(QueryMessageAttachmentInfosRequest(
        creationDateStart: creationDateStart?.toInt64(),
        creationDateEnd: creationDateEnd?.toInt64()));
    return n.toResponse((data) => data.storageResourceInfos.infos);
  }

  Future<Response<List<StorageResourceInfo>>>
      queryMessageAttachmentInfosInPrivateConversations(Set<Int64> userIds,
          {bool? areSharedByMe,
          DateTime? creationDateStart,
          DateTime? creationDateEnd}) async {
    final n = await _turmsClient.driver.send(QueryMessageAttachmentInfosRequest(
        userIds: userIds,
        areSharedByMe: areSharedByMe,
        creationDateStart: creationDateStart?.toInt64(),
        creationDateEnd: creationDateEnd?.toInt64()));
    return n.toResponse((data) => data.storageResourceInfos.infos);
  }

  Future<Response<List<StorageResourceInfo>>>
      queryMessageAttachmentInfosInGroupConversations(Set<Int64> groupIds,
          {Set<Int64>? userIds,
          DateTime? creationDateStart,
          DateTime? creationDateEnd}) async {
    final n = await _turmsClient.driver.send(QueryMessageAttachmentInfosRequest(
        groupIds: groupIds,
        userIds: userIds,
        creationDateStart: creationDateStart?.toInt64(),
        creationDateEnd: creationDateEnd?.toInt64()));
    return n.toResponse((data) => data.storageResourceInfos.infos);
  }

  // Base

  Future<Response<StorageUploadResult>> _upload(
      String url, Map<String, String> formData, Uint8List data, String id,
      {String? name, MediaType? mediaType}) async {
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of resource must not be empty');
    }
    final Uri uri;
    try {
      uri = Uri.parse(url);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The URL is illegal: $url',
          cause: e);
    }
    final request = http.MultipartRequest('POST', uri)
      ..fields.addAll(formData)
      ..fields['key'] = id
      ..files.add(http.MultipartFile.fromBytes('file', data,
          filename: name ?? id, contentType: mediaType));
    if (mediaType != null) {
      request.fields['Content-Type'] = mediaType.toString();
    }
    final http.StreamedResponse response;
    try {
      response = await _httpClient.send(request);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.httpError,
          reason:
              'Caught an error while sending an HTTP POST request to update the resource',
          cause: e);
    }
    if (response.isNotSuccessful) {
      throw ResponseException(
          code: ResponseStatusCode.httpNotSuccessfulResponse,
          reason:
              'Failed to upload the resource because the HTTP response status code is: ${response.statusCode}');
    }
    final String responseData;
    try {
      responseData = await response.stream.bytesToString();
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.invalidResponse,
          reason: 'Failed to get the response body as a string',
          cause: e);
    }
    Int64? idNum;
    try {
      idNum = Int64.parseInt(id);
    } on FormatException catch (_) {}
    final idStr = idNum == null ? id : null;
    return Response.value(
        StorageUploadResult(uri, response.headers, responseData, idNum, idStr));
  }

  Future<Response<void>> _deleteResource(StorageResourceType type,
      {Int64? idNum, String? idStr, Map<String, String>? extra}) async {
    if (extra?.isNotEmpty == true) {}
    final n = await _turmsClient.driver.send(DeleteResourceRequest(
        type: type, idNum: idNum, idStr: idStr, extra: extra));
    return n.toNullResponse();
  }

  Future<Response<StorageResource>> _queryResource(String url) async {
    final Uri uri;
    try {
      uri = Uri.parse(url);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The URL is illegal: $url',
          cause: e);
    }
    final http.Response response;
    try {
      response = await _httpClient.get(uri);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.httpError,
          reason:
              'Caught an error while sending an HTTP GET request to retrieve the resource',
          cause: e);
    }
    if (response.isNotSuccessful) {
      throw ResponseException(
          code: ResponseStatusCode.httpNotSuccessfulResponse,
          reason:
              'Failed to retrieve the resource because the HTTP response status code is: ${response.statusCode}');
    }
    return Response.value(
        StorageResource(uri, response.headers, response.bodyBytes));
  }

  Future<Response<Map<String, String>>> _queryResourceUploadInfo(
      StorageResourceType type,
      {Int64? idNum,
      String? idStr,
      String? name,
      String? mediaType,
      Map<String, String>? extra}) async {
    final n = await _turmsClient.driver.send(QueryResourceUploadInfoRequest(
        type: type,
        idNum: idNum,
        idStr: idStr,
        name: name,
        mediaType: mediaType,
        extra: extra));
    return n.toResponse((data) => data.stringsWithVersion.strings.toMap());
  }

  Future<Response<Map<String, String>>> _queryResourceDownloadInfo(
      StorageResourceType type,
      {Int64? idNum,
      String? idStr,
      Map<String, String>? extra}) async {
    final n = await _turmsClient.driver.send(QueryResourceDownloadInfoRequest(
        type: type, idNum: idNum, idStr: idStr, extra: extra));
    return n.toResponse((data) => data.stringsWithVersion.strings.toMap());
  }

  String _getBucketName(StorageResourceType resourceType) =>
      _resourceTypeToBucketName[resourceType]!;

  MediaType _parseMediaType(String mediaType) {
    try {
      return MediaType.parse(mediaType);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The media type is illegal',
          cause: e);
    }
  }

  String _getAndRemoveResourceUrl(Map<String, String> data, String urlKeyName) {
    final url = data.remove(urlKeyName);
    if (url == null) {
      throw ResponseException(
          code: ResponseStatusCode.dataNotFound,
          reason:
              'Could not get the resource URL because the key "$urlKeyName" does not exist in the data: ${data}');
    }
    return url;
  }
}
