import 'package:http/http.dart';

class HttpResponseException implements Exception {
  HttpResponseException(this.response);

  final Response response;
}
