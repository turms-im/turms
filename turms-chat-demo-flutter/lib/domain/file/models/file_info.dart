class FileInfo {
  FileInfo(
      {required this.name,
      required this.uploadDate,
      required this.uploader,
      required this.type,
      required this.size});

  final String name;
  final DateTime uploadDate;
  final String uploader;
  final String type;
  final int size;
}
