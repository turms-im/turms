import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';
import '../model/constant/content_type.pbenum.dart';
import '../model/request/storage/delete_resource_request.pb.dart';
import '../model/request/storage/query_signed_get_url_request.pb.dart';
import '../model/request/storage/query_signed_put_url_request.pb.dart';
import '../model/turms_business_exception.dart';
import '../model/turms_status_code.dart';

class StorageService {
  // TODO: destroy
  static final HttpClient _httpClient = HttpClient();

  final TurmsClient _turmsClient;
  final Uri serverUrl;

  StorageService(this._turmsClient, String? storageServerUrl)
      : serverUrl = Uri.parse(storageServerUrl ?? 'http://localhost:9000');

  // Profile picture

  Future<String> queryProfilePictureUrlForAccess(Int64 userId) async =>
      '$serverUrl/${_getBucketName(ContentType.PROFILE)}/$userId';

  Future<Uint8List> queryProfilePicture(Int64 userId) async {
    final url = await queryProfilePictureUrlForAccess(userId);
    return _getBytesFromGetUrl(url);
  }

  Future<String> queryProfilePictureUrlForUpload(int pictureSize) async {
    final userId = _turmsClient.userService.userInfo?.userId;
    if (userId == null) {
      throw TurmsBusinessException.fromCode(
          TurmsStatusCode.queryProfileUrlToUpdateBeforeLogin);
    } else {
      return _getSignedPutUrl(ContentType.PROFILE, pictureSize, keyNum: userId);
    }
  }

  Future<String> uploadProfilePicture(Uint8List bytes) async {
    final url = await queryProfilePictureUrlForUpload(bytes.length);
    return _upload(url, bytes);
  }

  Future<void> deleteProfile() => _deleteResource(ContentType.PROFILE);

  // Group profile picture

  Future<String> queryGroupProfilePictureUrlForAccess(Int64 groupId) async =>
      '$serverUrl/${_getBucketName(ContentType.GROUP_PROFILE)}/$groupId';

  Future<Uint8List> queryGroupProfilePicture(Int64 groupId) async {
    final url = await queryGroupProfilePictureUrlForAccess(groupId);
    return _getBytesFromGetUrl(url);
  }

  Future<String> queryGroupProfilePictureUrlForUpload(
          int pictureSize, Int64 groupId) =>
      _getSignedPutUrl(ContentType.GROUP_PROFILE, pictureSize, keyNum: groupId);

  Future<String> uploadGroupProfilePicture(
      Uint8List bytes, Int64 groupId) async {
    final url =
        await queryGroupProfilePictureUrlForUpload(bytes.length, groupId);
    return _upload(url, bytes);
  }

  Future<void> deleteGroupProfile(Int64 groupId) =>
      _deleteResource(ContentType.GROUP_PROFILE, keyNum: groupId);

  // Message attachment

  Future<String> queryAttachmentUrlForAccess(Int64 messageId, {String? name}) =>
      _getSignedGetUrl(ContentType.ATTACHMENT, keyStr: name, keyNum: messageId);

  Future<Uint8List> queryAttachment(Int64 messageId, {String? name}) async {
    final url = await queryAttachmentUrlForAccess(messageId, name: name);
    return _getBytesFromGetUrl(url);
  }

  Future<String> queryAttachmentUrlForUpload(
          Int64 messageId, int attachmentSize) =>
      _getSignedPutUrl(ContentType.ATTACHMENT, attachmentSize,
          keyNum: messageId);

  Future<String> uploadAttachment(Int64 messageId, Uint8List bytes) async {
    final url = await queryAttachmentUrlForUpload(messageId, bytes.length);
    return _upload(url, bytes);
  }

  // Base

  Future<String> _getSignedGetUrl(ContentType contentType,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QuerySignedGetUrlRequest(
        contentType: contentType, keyStr: keyStr, keyNum: keyNum));
    return n.data.url;
  }

  Future<String> _getSignedPutUrl(ContentType contentType, int size,
      {String? keyStr, Int64? keyNum}) async {
    final n = await _turmsClient.driver.send(QuerySignedPutUrlRequest(
        contentType: contentType,
        contentLength: Int64(size),
        keyStr: keyStr,
        keyNum: keyNum));
    return n.data.url;
  }

  Future<void> _deleteResource(ContentType contentType,
          {String? keyStr, Int64? keyNum}) =>
      _turmsClient.driver.send(DeleteResourceRequest(
          contentType: contentType, keyStr: keyStr, keyNum: keyNum));

  Future<Uint8List> _getBytesFromGetUrl(String url) async {
    final request = await _httpClient.getUrl(serverUrl);
    final response = await request.close();
    if (response.statusCode == 200) {
      final bytes =
          await response.reduce((pre, element) => pre..addAll(element));
      return Uint8List.fromList(bytes);
    } else {
      throw TurmsBusinessException.fromCode(TurmsStatusCode.invalidResponse);
    }
  }

  Future<String> _upload(String url, Uint8List bytes) async {
    final request = await _httpClient.putUrl(serverUrl);
    request.add(bytes);
    final response = await request.close();
    if (response.statusCode == 200) {
      return response
          .transform(utf8.decoder)
          .reduce((previous, element) => previous + element);
    } else {
      throw TurmsBusinessException.fromCode(TurmsStatusCode.invalidResponse);
    }
  }

  String _getBucketName(ContentType contentType) =>
      contentType.name.toLowerCase().replaceAll('_', '-');
}
