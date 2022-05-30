import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';

class StorageService {
  // TODO: destroy
  static final HttpClient _httpClient = HttpClient();

  final TurmsClient _turmsClient;
  final Uri serverUrl;

  StorageService(this._turmsClient, String? storageServerUrl)
      : serverUrl = Uri.parse(storageServerUrl ?? 'http://localhost:9000');

  // Profile picture

  Future<Response<String>> queryProfilePictureUrlForAccess(
      Int64 userId) async =>
      Response.value(
          '$serverUrl/${_getBucketName(ContentType.PROFILE)}/$userId');

  Future<Response<Uint8List>> queryProfilePicture(Int64 userId) async {
    final response = await queryProfilePictureUrlForAccess(userId);
    return _getBytesFromGetUrl(response.data!);
  }

  Future<Response<String>> queryProfilePictureUrlForUpload(
      int pictureSize) async {
    final userId = _turmsClient.userService.userInfo?.userId;
    if (userId == null) {
      throw ResponseException.fromCode(
          ResponseStatusCode.queryProfileUrlToUpdateBeforeLogin);
    } else {
      return _getSignedPutUrl(ContentType.PROFILE, pictureSize, keyNum: userId);
    }
  }

  Future<Response<String>> uploadProfilePicture(Uint8List bytes) async {
    final response = await queryProfilePictureUrlForUpload(bytes.length);
    return _upload(response.data!, bytes);
  }

  Future<Response<void>> deleteProfile() =>
      _deleteResource(ContentType.PROFILE);

  // Group profile picture

  Future<Response<String>> queryGroupProfilePictureUrlForAccess(
      Int64 groupId) async =>
      Response.value(
          '$serverUrl/${_getBucketName(ContentType.GROUP_PROFILE)}/$groupId');

  Future<Response<Uint8List>> queryGroupProfilePicture(Int64 groupId) async {
    final response = await queryGroupProfilePictureUrlForAccess(groupId);
    return _getBytesFromGetUrl(response.data!);
  }

  Future<Response<String>> queryGroupProfilePictureUrlForUpload(
      int pictureSize, Int64 groupId) =>
      _getSignedPutUrl(ContentType.GROUP_PROFILE, pictureSize, keyNum: groupId);

  Future<Response<String>> uploadGroupProfilePicture(
      Uint8List bytes, Int64 groupId) async {
    final response =
    await queryGroupProfilePictureUrlForUpload(bytes.length, groupId);
    return _upload(response.data!, bytes);
  }

  Future<Response<void>> deleteGroupProfile(Int64 groupId) =>
      _deleteResource(ContentType.GROUP_PROFILE, keyNum: groupId);

  // Message attachment

  Future<Response<String>> queryAttachmentUrlForAccess(Int64 messageId,
      {String? name}) =>
      _getSignedGetUrl(ContentType.ATTACHMENT, keyStr: name, keyNum: messageId);

  Future<Response<Uint8List>> queryAttachment(Int64 messageId,
      {String? name}) async {
    final response = await queryAttachmentUrlForAccess(messageId, name: name);
    return _getBytesFromGetUrl(response.data!);
  }

  Future<Response<String>> queryAttachmentUrlForUpload(
      Int64 messageId, int attachmentSize) =>
      _getSignedPutUrl(ContentType.ATTACHMENT, attachmentSize,
          keyNum: messageId);

  Future<Response<String>> uploadAttachment(
      Int64 messageId, Uint8List bytes) async {
    final response = await queryAttachmentUrlForUpload(messageId, bytes.length);
    return _upload(response.data!, bytes);
  }

  // Base

  Future<Response<String>> _getSignedGetUrl(ContentType contentType,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QuerySignedGetUrlRequest(
        contentType: contentType, keyStr: keyStr, keyNum: keyNum));
    return n.toResponse((data) => data.url);
  }

  Future<Response<String>> _getSignedPutUrl(ContentType contentType, int size,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QuerySignedPutUrlRequest(
        contentType: contentType,
        contentLength: Int64(size),
        keyStr: keyStr,
        keyNum: keyNum));
    return n.toResponse((data) => data.url);
  }

  Future<Response<void>> _deleteResource(ContentType contentType,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(DeleteResourceRequest(
        contentType: contentType, keyStr: keyStr, keyNum: keyNum));
    return n.toNullResponse();
  }

  Future<Response<Uint8List>> _getBytesFromGetUrl(String url) async {
    final request = await _httpClient.getUrl(Uri.parse(url));
    final response = await request.close();
    if (response.statusCode == 200) {
      final bytes =
      await response.reduce((pre, element) => pre..addAll(element));
      return Response.value(Uint8List.fromList(bytes));
    } else {
      throw ResponseException.fromCode(ResponseStatusCode.invalidResponse);
    }
  }

  Future<Response<String>> _upload(String url, Uint8List bytes) async {
    final request = await _httpClient.putUrl(Uri.parse(url));
    request.add(bytes);
    final response = await request.close();
    if (response.statusCode == 200) {
      final data = await response
          .transform(utf8.decoder)
          .reduce((previous, element) => previous + element);
      return Response.value(data);
    } else {
      throw ResponseException.fromCode(ResponseStatusCode.invalidResponse);
    }
  }

  String _getBucketName(ContentType contentType) =>
      contentType.name.toLowerCase().replaceAll('_', '-');
}
