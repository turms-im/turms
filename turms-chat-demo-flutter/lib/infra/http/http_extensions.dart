import 'package:http/http.dart';

extension HttpResponseExtensions on Response {
  String get description {
    // TODO: map status code to localized text.
    if (300 >= statusCode && statusCode > 200) {
      return statusCode.toString();
    }
    final contentType = headers['content-type'] ?? headers['Content-Type'];
    if (contentType == 'text/plain') {
      return '$statusCode: $body';
    }
    return statusCode.toString();
  }
}
