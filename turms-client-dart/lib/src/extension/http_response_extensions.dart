import 'package:http/http.dart' as http;

extension HttpResponseExtensions on http.BaseResponse {
  bool get isSuccessful => (statusCode ~/ 100) == 2;
  bool get isNotSuccessful => !isSuccessful;
}
