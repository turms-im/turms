import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';
import 'package:http/http.dart' as http;
import 'package:http_parser/http_parser.dart';

import '../../turms_client.dart';
import '../extension/http_response_extensions.dart';
import '../model/storage_resource.dart';
import '../model/storage_upload_result.dart';

typedef ResourceUrlExtractor = String Function(Map<String, String>);

class StorageService {
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

  Future<Response<StorageUploadResult>> uploadUserProfilePicture(
      String mediaType, Uint8List data,
      {String urlKeyName = _defaultUrlKeyName}) async {
    final type = _parseMediaType(mediaType);
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of user profile picture must not be empty');
    }
    final userId = _turmsClient.userService.userInfo?.userId;
    if (userId == null) {
      throw ResponseException(
          code: ResponseStatusCode.uploadUserProfilePictureBeforeLogin);
    }
    final uploadInfo = await queryUserProfilePictureUploadInfo();
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    return _upload(url, uploadInfo.data, userId.toString(), type, data);
  }

  Future<Response<void>> deleteUserProfilePicture() =>
      _deleteResource(StorageResourceType.USER_PROFILE_PICTURE);

  Future<Response<StorageResource>> queryUserProfilePicture(Int64 userId,
      {String urlKeyName = _defaultUrlKeyName}) async {
    final downloadInfo = await queryUserProfilePictureDownloadInfo(userId,
        urlKeyName: urlKeyName);
    final url = _getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>>
      queryUserProfilePictureUploadInfo() async {
    final userId = _turmsClient.userService.userInfo?.userId;
    if (userId == null) {
      throw ResponseException(
          code: ResponseStatusCode.queryUserProfilePictureBeforeLogin);
    }
    return _queryResourceUploadInfo(StorageResourceType.USER_PROFILE_PICTURE);
  }

  Future<Response<Map<String, String>>> queryUserProfilePictureDownloadInfo(
      Int64 userId,
      {bool fetch = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (fetch) {
      return _queryResourceDownloadInfo(
          StorageResourceType.USER_PROFILE_PICTURE,
          keyNum: userId);
    }
    return Response.value({
      urlKeyName:
          '$serverUrl/${_getBucketName(StorageResourceType.USER_PROFILE_PICTURE)}/$userId'
    });
  }

  // Group profile picture

  Future<Response<StorageUploadResult>> uploadGroupProfilePicture(
      Int64 groupId, String mediaType, Uint8List data,
      {String urlKeyName = _defaultUrlKeyName}) async {
    final type = _parseMediaType(mediaType);
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of group profile picture must not be empty');
    }
    final uploadInfo = await queryGroupProfilePictureUploadInfo(groupId);
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    return _upload(url, uploadInfo.data, groupId.toString(), type, data);
  }

  Future<Response<void>> deleteGroupProfilePicture(Int64 groupId) =>
      _deleteResource(StorageResourceType.GROUP_PROFILE_PICTURE,
          keyNum: groupId);

  Future<Response<StorageResource>> queryGroupProfilePicture(Int64 groupId,
      {String urlKeyName = _defaultUrlKeyName}) async {
    final downloadInfo = await queryGroupProfilePictureDownloadInfo(groupId,
        urlKeyName: urlKeyName);
    final url = _getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>> queryGroupProfilePictureUploadInfo(
          Int64 groupId) =>
      _queryResourceUploadInfo(StorageResourceType.GROUP_PROFILE_PICTURE,
          keyNum: groupId);

  Future<Response<Map<String, String>>> queryGroupProfilePictureDownloadInfo(
      Int64 groupId,
      {bool fetch = false,
      String urlKeyName = _defaultUrlKeyName}) async {
    if (fetch) {
      return _queryResourceDownloadInfo(
          StorageResourceType.GROUP_PROFILE_PICTURE,
          keyNum: groupId);
    }
    return Response.value({
      urlKeyName:
          '$serverUrl/${_getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/$groupId'
    });
  }

  // Message attachment

  Future<Response<StorageUploadResult>> uploadMessageAttachment(
      Int64 messageId, String mediaType, Uint8List data,
      {String? name, String urlKeyName = _defaultUrlKeyName}) async {
    final type = _parseMediaType(mediaType);
    if (data.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: 'The data of message attachment must not be empty');
    }
    final uploadInfo = await queryMessageAttachmentUploadInfo(messageId);
    final url = _getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
    final resourceName =
        name == null ? messageId.toString() : '$messageId/$name';
    return _upload(url, uploadInfo.data, resourceName, type, data);
  }

  Future<Response<void>> deleteMessageAttachment(Int64 messageId,
          {String? name}) =>
      _deleteResource(StorageResourceType.MESSAGE_ATTACHMENT,
          keyNum: messageId, keyStr: name);

  Future<Response<StorageResource>> queryMessageAttachment(Int64 messageId,
      {String? name, String urlKeyName = _defaultUrlKeyName}) async {
    final response =
        await queryMessageAttachmentDownloadInfo(messageId, name: name);
    final url = _getAndRemoveResourceUrl(response.data, urlKeyName);
    return _queryResource(url);
  }

  Future<Response<Map<String, String>>> queryMessageAttachmentUploadInfo(
          Int64 messageId,
          {String? name}) =>
      _queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
          keyStr: name, keyNum: messageId);

  Future<Response<Map<String, String>>> queryMessageAttachmentDownloadInfo(
          Int64 messageId,
          {String? name}) =>
      _queryResourceDownloadInfo(StorageResourceType.MESSAGE_ATTACHMENT,
          keyStr: name, keyNum: messageId);

  // Base

  Future<Response<StorageUploadResult>> _upload(
      String url,
      Map<String, String> formData,
      String resourceName,
      MediaType mediaType,
      Uint8List data) async {
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
      ..fields['key'] = resourceName
      ..fields['Content-Type'] = mediaType.toString()
      ..files.add(http.MultipartFile.fromBytes('file', data,
          filename: resourceName, contentType: mediaType));
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
    return Response.value(
        StorageUploadResult(uri, response.headers, responseData));
  }

  Future<Response<void>> _deleteResource(StorageResourceType type,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(
        DeleteResourceRequest(type: type, keyStr: keyStr, keyNum: keyNum));
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
      {String? keyStr,
      Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QueryResourceUploadInfoRequest(
        type: type, keyStr: keyStr, keyNum: keyNum));
    return n.toResponse((data) => data.stringsWithVersion.strings.toMap());
  }

  Future<Response<Map<String, String>>> _queryResourceDownloadInfo(
      StorageResourceType type,
      {String? keyStr,
      Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QueryResourceDownloadInfoRequest(
        type: type, keyStr: keyStr, keyNum: keyNum));
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
          code: ResponseStatusCode.invalidResponse,
          reason:
              'Cannot get the resource URL because the key "$urlKeyName" doesn\'t exist');
    }
    return url;
  }
}
