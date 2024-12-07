import 'dart:async';
import 'dart:convert';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:http/http.dart';

import '../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../infra/env/env_vars.dart';
import 'models/gif.dart';
import 'models/languages.dart';
import 'models/rating.dart';
import 'models/response.dart';
import 'models/type.dart';

/// Reference: https://developers.giphy.com/docs/api/endpoint
class GiphyClient {
  GiphyClient({required String apiKey, required this.randomId})
      : _apiKey = apiKey;

  static final baseUri = Uri(scheme: 'https', host: 'api.giphy.com');
  static const String _apiVersion = 'v1';
  static final Client _client = Client();

  final String _apiKey;
  final String randomId;

  Future<GiphyResponse> trending({
    int offset = 0,
    int limit = 30,
    String rating = GiphyRating.g,
    String lang = GiphyLanguage.english,
    GiphyType type = GiphyType.stickers,
  }) async =>
      _fetchCollection(
        baseUri.replace(
          path: '$_apiVersion/${type.name}/trending',
          queryParameters: <String, String>{
            'offset': '$offset',
            'limit': '$limit',
            'rating': rating,
            'lang': lang
          },
        ),
      );

  Future<GiphyResponse> search(
    String query, {
    int offset = 0,
    int limit = 30,
    String rating = GiphyRating.g,
    String lang = GiphyLanguage.english,
    GiphyType type = GiphyType.stickers,
  }) async =>
      _fetchCollection(
        baseUri.replace(
          path: '$_apiVersion/${type.name}/search',
          queryParameters: <String, String>{
            'q': query,
            'offset': '$offset',
            'limit': '$limit',
            'rating': rating,
            'lang': lang,
          },
        ),
      );

  Future<GiphyResponse> emojis({
    int offset = 0,
    int limit = 30,
    String rating = GiphyRating.g,
    String lang = GiphyLanguage.english,
  }) async =>
      _fetchCollection(
        baseUri.replace(
          path: '$_apiVersion/${GiphyType.emoji.name}',
          queryParameters: <String, String>{
            'offset': '$offset',
            'limit': '$limit',
            'rating': rating,
            'lang': lang,
          },
        ),
      );

  Future<GiphyGif> random({
    required String tag,
    String rating = GiphyRating.g,
    GiphyType type = GiphyType.stickers,
  }) async =>
      _fetchGif(
        baseUri.replace(
          path: '$_apiVersion/${type.name}/random',
          queryParameters: <String, String>{
            'tag': tag,
            'rating': rating,
          },
        ),
      );

  Future<GiphyGif> byId(String id) async =>
      _fetchGif(baseUri.replace(path: 'v1/gifs/$id'));

  Future<String> getRandomId() async =>
      _getRandomId(baseUri.replace(path: 'v1/randomid'));

  Future<GiphyGif> _fetchGif(Uri uri) async {
    final response = await _getWithAuthorization(uri);
    final body = json.decode(response.body) as Map<String, dynamic>;
    return GiphyGif.fromJson(body['data'] as Map<String, dynamic>);
  }

  Future<GiphyResponse> _fetchCollection(Uri uri) async {
    final response = await _getWithAuthorization(uri);
    final body = json.decode(response.body) as Map<String, dynamic>;
    return GiphyResponse.fromJson(body);
  }

  Future<String> _getRandomId(Uri uri) async {
    final response = await _getWithAuthorization(uri);
    final body = json.decode(response.body) as Map<String, dynamic>;
    final data = body['data'] as Map<String, dynamic>;
    return data['random_id'] as String;
  }

  Future<Response> _getWithAuthorization(Uri uri) async {
    final response = await _client.get(uri.replace(
        queryParameters: Map<String, String>.from(uri.queryParameters)
          ..putIfAbsent('api_key', () => _apiKey)
          ..putIfAbsent('random_id', () => randomId)));
    if (response.statusCode == 200) {
      return response;
    }
    throw Exception(
        'Code: ${response.statusCode}. Response body: ${response.body}');
  }
}

const _apiKey = EnvVars.giphyApiKey;

final giphyClientProvider = StateProvider<GiphyClient>((ref) {
  final randomId = ref.watch(loggedInUserViewModel)!.userId.toString() ?? '';
  return GiphyClient(apiKey: _apiKey, randomId: randomId);
});
