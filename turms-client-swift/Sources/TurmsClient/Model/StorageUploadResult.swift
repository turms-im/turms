import Foundation

public struct StorageUploadResult {
    public let uri: URL
    public let metadata: [String: String]
    public let data: String
    public let resourceIdNum: Int64?
    public let resourceIdStr: String?
}