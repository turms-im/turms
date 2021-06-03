import Foundation
import PromiseKit

public class StorageService {
    private weak var turmsClient: TurmsClient!
    var serverUrl: String!

    init(_ turmsClient: TurmsClient, storageServerUrl: String? = nil) {
        self.turmsClient = turmsClient
        self.serverUrl = storageServerUrl ?? "http://localhost:9000"
    }

    // Profile picture

    public func queryProfilePictureUrlForAccess(_ userId: Int64) -> Promise<String> {
        do {
            let name = try StorageService.getBucketName(.profile)
            let url = "\(serverUrl!)/\(name)/\(userId)"
            return Promise.value(url)
        } catch {
            return Promise(error: error)
        }
    }

    public func queryProfilePicture(_ userId: Int64) -> Promise<Data> {
        return queryProfilePictureUrlForAccess(userId)
            .then {
                self.getBytesFromGetUrl($0)
            }
    }

    public func queryProfilePictureUrlForUpload(_ pictureSize: Int) -> Promise<String> {
        if let userId = turmsClient.userService.userInfo?.userId {
            return getSignedPutUrl(contentType: .profile, size: Int64(pictureSize), keyNum: userId)
        } else {
            return Promise(error: TurmsBusinessError(TurmsStatusCode.queryProfileUrlToUpdateBeforeLogin))
        }
    }

    public func uploadProfilePicture(_ data: Data) -> Promise<String> {
        return queryProfilePictureUrlForUpload(data.count)
            .then {
                self.upload(url: $0, data: data)
            }
    }

    public func deleteProfile() -> Promise<Void> {
        return deleteResource(contentType: .profile)
    }

    // Group profile picture

    public func queryGroupProfilePictureUrlForAccess(_ groupId: Int64) -> Promise<String> {
        do {
            let name = try StorageService.getBucketName(.groupProfile)
            let url = "\(serverUrl!)/\(name)/\(groupId)"
            return Promise.value(url)
        } catch {
            return Promise(error: error)
        }
    }

    public func queryGroupProfilePicture(_ groupId: Int64) -> Promise<Data> {
        return queryGroupProfilePictureUrlForAccess(groupId)
            .then {
                self.getBytesFromGetUrl($0)
            }
    }

    public func queryGroupProfilePictureUrlForUpload(pictureSize: Int, groupId: Int64) -> Promise<String> {
        return getSignedPutUrl(contentType: .groupProfile, size: Int64(pictureSize), keyNum: groupId)
    }

    public func uploadGroupProfilePicture(data: Data, groupId: Int64) -> Promise<String> {
        return queryGroupProfilePictureUrlForUpload(pictureSize: data.count, groupId: groupId)
            .then {
                self.upload(url: $0, data: data)
            }
    }

    public func deleteGroupProfile(_ groupId: Int64) -> Promise<Void> {
        return deleteResource(contentType: .groupProfile, keyNum: groupId)
    }

    // Message attachment

    public func queryAttachmentUrlForAccess(messageId: Int64, name: String? = nil) -> Promise<String> {
        return getSignedGetUrl(contentType: .attachment, keyStr: name, keyNum: messageId)
    }

    public func queryAttachment(messageId: Int64, name: String? = nil) -> Promise<Data> {
        return queryAttachmentUrlForAccess(messageId: messageId, name: name)
            .then {
                self.getBytesFromGetUrl($0)
            }
    }

    public func queryAttachmentUrlForUpload(messageId: Int64, attachmentSize: Int64) -> Promise<String> {
        return getSignedPutUrl(contentType: .attachment, size: attachmentSize, keyNum: messageId)
    }

    public func uploadAttachment(messageId: Int64, data: Data) -> Promise<String> {
        return queryAttachmentUrlForUpload(messageId: messageId, attachmentSize: Int64(data.count))
            .then {
                self.upload(url: $0, data: data)
            }
    }

    // Base

    private func getSignedGetUrl(contentType: ContentType, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<String> {
        return turmsClient.driver
            .send {
                $0.querySignedGetURLRequest = .with {
                    $0.contentType = contentType
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .map {
                $0.data.url
            }
    }

    private func getSignedPutUrl(contentType: ContentType, size: Int64, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<String> {
        return turmsClient.driver
            .send {
                $0.querySignedPutURLRequest = .with {
                    $0.contentType = contentType
                    $0.contentLength = size
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .map {
                $0.data.url
            }
    }

    private func deleteResource(contentType: ContentType, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteResourceRequest = .with {
                    $0.contentType = contentType
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .asVoid()
    }

    private func getBytesFromGetUrl(_ url: String) -> Promise<Data> {
        return Promise { seal in
            let reqUrl = URL(string: url)!
            var request = URLRequest(url: reqUrl)
            request.httpMethod = "GET"
            URLSession.shared.dataTask(with: request) { data, response, error in
                if let error = error {
                    seal.reject(error)
                } else if let response = response as? HTTPURLResponse {
                    if response.statusCode == 200 {
                        seal.fulfill(data!)
                    } else {
                        seal.reject(TurmsBusinessError(4000))
                    }
                } else {
                    seal.reject(TurmsBusinessError(4000))
                }
            }.resume()
        }
    }

    private func upload(url: String, data: Data) -> Promise<String> {
        return Promise { seal in
            let reqUrl = URL(string: url)!
            var request = URLRequest(url: reqUrl)
            request.httpMethod = "PUT"
            request.httpBody = data
            URLSession.shared.dataTask(with: request) { _, response, error in
                if let error = error {
                    seal.reject(error)
                } else if let response = response as? HTTPURLResponse {
                    if response.statusCode == 200 {
                        seal.fulfill(url)
                    } else {
                        seal.reject(TurmsBusinessError(4000))
                    }
                } else {
                    seal.reject(TurmsBusinessError(4000))
                }
            }.resume()
        }
    }

    private static func getBucketName(_ contentType: ContentType) throws -> String {
        // Use hardcode because the methods of ContentType._protobuf_nameMap cannot be accessed
        switch contentType {
            case .profile:
                return "profile"
            case .groupProfile:
                return "group-profile"
            case .attachment:
                return "attachment"
            default:
                let reason = "Unknown content type \(contentType)"
                throw TurmsBusinessError(TurmsStatusCode.illegalArgument, reason)
            }
    }
}
