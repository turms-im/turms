import Foundation
import PromiseKit

public class StorageService {
    private static let DEFAULT_URL_KEY_NAME = "url"
    private static let RESOURCE_TYPE_TO_BUCKET_NAME: [StorageResourceType: String] = Dictionary(uniqueKeysWithValues: StorageResourceType
        .allCases.map { ($0, "\($0)".camelCaseToSnakeCase) })
    private weak var turmsClient: TurmsClient!
    var serverUrl: String

    init(_ turmsClient: TurmsClient, storageServerUrl: String? = nil) {
        self.turmsClient = turmsClient
        serverUrl = storageServerUrl ?? "http://localhost:9000"
    }

    // User profile picture

    public func uploadUserProfilePicture(mediaType: String, data: Data, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        if data.isEmpty {
            return Promise(error: ResponseError(
                code: .illegalArgument,
                reason: "The data of user profile picture must not be empty"
            ))
        }
        guard let userId = turmsClient.userService.userInfo?.userId else {
            return Promise(error: ResponseError(code: .uploadUserProfilePictureBeforeLogin))
        }
        return queryUserProfilePictureUploadInfo()
            .then { uploadInfo -> Promise<Response<StorageUploadResult>> in
                var infoData = uploadInfo.data
                let url = try self.getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.upload(url: url, formData: infoData, resourceName: "\(userId)", mediaType: mediaType, data: data)
            }
    }

    public func deleteUserProfilePicture() -> Promise<Response<Void>> {
        return deleteResource(type: .userProfilePicture)
    }

    public func queryUserProfilePicture(userId: Int64, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryUserProfilePictureDownloadInfo(userId: userId, urlKeyName: urlKeyName)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryUserProfilePictureUploadInfo() -> Promise<Response<[String: String]>> {
        if let userId = turmsClient.userService.userInfo?.userId {
            return getResourceUploadInfo(type: .userProfilePicture)
        } else {
            return Promise(error: ResponseError(code: .queryUserProfilePictureBeforeLogin))
        }
    }

    public func queryUserProfilePictureDownloadInfo(userId: Int64, fetch: Bool = false, urlKeyName: String? = nil) -> Promise<Response<[String: String]>> {
        if fetch {
            return queryResourceDownloadInfo(type: .userProfilePicture, keyNum: userId)
        }
        let url = "\(serverUrl)/\(getBucketName(.userProfilePicture))/\(userId)"
        return Promise.value(Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ]))
    }

    // Group profile picture

    public func uploadGroupProfilePicture(groupId: Int64, mediaType: String, data: Data, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        if data.isEmpty {
            return Promise(error: ResponseError(
                code: .illegalArgument,
                reason: "The data of group profile picture must not be empty"
            ))
        }
        return queryGroupProfilePictureUploadInfo(groupId: groupId)
            .then { uploadInfo -> Promise<Response<StorageUploadResult>> in
                var infoData = uploadInfo.data
                let url = try self.getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.upload(url: url, formData: infoData, resourceName: "\(groupId)", mediaType: mediaType, data: data)
            }
    }

    public func deleteGroupProfilePicture(_ groupId: Int64) -> Promise<Response<Void>> {
        return deleteResource(type: .groupProfilePicture, keyNum: groupId)
    }

    public func queryGroupProfilePicture(groupId: Int64, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryGroupProfilePictureDownloadInfo(groupId: groupId, urlKeyName: urlKeyName)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryGroupProfilePictureUploadInfo(groupId: Int64) -> Promise<Response<[String: String]>> {
        return getResourceUploadInfo(type: .groupProfilePicture, keyNum: groupId)
    }

    public func queryGroupProfilePictureDownloadInfo(groupId: Int64, fetch: Bool = false, urlKeyName: String? = nil) -> Promise<Response<[String: String]>> {
        if fetch {
            return queryResourceDownloadInfo(type: .groupProfilePicture, keyNum: groupId)
        }
        let url = "\(serverUrl)/\(getBucketName(.groupProfilePicture))/\(groupId)"
        return Promise.value(Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ]))
    }

    // Message attachment

    public func uploadMessageAttachment(messageId: Int64, mediaType: String, data: Data, name: String? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        if data.isEmpty {
            return Promise(error: ResponseError(
                code: .illegalArgument,
                reason: "The data of message attachment must not be empty"
            ))
        }
        return queryMessageAttachmentUploadInfo(messageId: messageId)
            .then { uploadInfo -> Promise<Response<StorageUploadResult>> in
                var infoData = uploadInfo.data
                let url = try self.getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                let resourceName = name == nil ? "\(messageId)" : "\(messageId)/\(name!)"
                return self.upload(url: url, formData: infoData, resourceName: resourceName, mediaType: mediaType, data: data)
            }
    }

    public func deleteMessageAttachment(messageId: Int64, name: String? = nil) -> Promise<Response<Void>> {
        return deleteResource(type: .messageAttachment, keyStr: name, keyNum: messageId)
    }

    public func queryMessageAttachment(messageId: Int64, name: String? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryMessageAttachmentDownloadInfo(messageId: messageId, name: name)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryMessageAttachmentUploadInfo(messageId: Int64, name: String? = nil) -> Promise<Response<[String: String]>> {
        return getResourceUploadInfo(type: .messageAttachment, keyStr: name, keyNum: messageId)
    }

    public func queryMessageAttachmentDownloadInfo(messageId: Int64, name: String? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceDownloadInfo(type: .messageAttachment, keyStr: name, keyNum: messageId)
    }

    // Base

    private func upload(url: String, formData: [String: String], resourceName: String, mediaType: String, data: Data) -> Promise<Response<StorageUploadResult>> {
        return Promise { seal in
            if data.isEmpty {
                seal.reject(ResponseError(code: .illegalArgument, reason: "The data of resource must not be empty"))
                return
            }
            let httpUrl = URL(string: url)
            guard let httpUrl = httpUrl else {
                seal.reject(ResponseError(code: .illegalArgument, reason: "The URL is illegal: \(url)"))
                return
            }
            let request = MultipartFormDataRequest(url: httpUrl)
            formData.forEach { key, value in
                request.addTextField(named: key, value: value)
            }
            request.addTextField(named: "key", value: resourceName)
            request.addTextField(named: "Content-Type", value: mediaType)
            request.addDataField(fieldName: "file", fileName: resourceName, data: data, mediaType: mediaType)

            URLSession.shared.dataTask(with: request, completionHandler: { responseData, response, error in
                if let error = error {
                    seal.reject(ResponseError(
                        code: .httpError,
                        reason: "Caught an error while sending an HTTP POST request to update the resource",
                        cause: error
                    ))
                } else if let response = response as? HTTPURLResponse {
                    if response.isSuccessful {
                        let text = responseData == nil ? "" : String(decoding: responseData!, as: UTF8.self)
                        let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map { (String(describing: $0.key), String(describing: $0.value)) })
                        seal.fulfill(Response.value(StorageUploadResult(uri: httpUrl, metadata: headers, data: text)))
                    } else {
                        seal.reject(ResponseError(
                            code: .httpNotSuccessfulResponse,
                            reason: "Failed to upload the resource because the HTTP response status code is: \(response.statusCode)"
                        ))
                    }
                } else {
                    seal.reject(ResponseError(
                        code: .invalidResponse,
                        reason: "Expected the response to be a HTTP response, but got \(response?.className ?? "nil")"
                    ))
                }
            }).resume()
        }
    }

    private func deleteResource(type: StorageResourceType, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteResourceRequest = .with {
                    $0.type = type
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    private func getResource(_ url: String) -> Promise<Response<StorageResource>> {
        return Promise { seal in
            let reqUrl = URL(string: url)
            guard let reqUrl = reqUrl else {
                seal.reject(ResponseError(
                    code: .illegalArgument,
                    reason: "The URL is illegal: \(url)"
                ))
                return
            }
            var request = URLRequest(url: reqUrl)
            request.httpMethod = "GET"
            URLSession.shared.dataTask(with: request) { responseData, response, error in
                if let error = error {
                    seal.reject(ResponseError(
                        code: .httpError,
                        reason: "Caught an error while sending an HTTP GET request to retrieve the resource",
                        cause: error
                    ))
                } else if let response = response as? HTTPURLResponse {
                    if response.isSuccessful {
                        let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map { (String(describing: $0.key), String(describing: $0.value)) })
                        if let responseData = responseData {
                            seal.fulfill(Response.value(StorageResource(uri: reqUrl, metadata: headers, data: responseData)))
                        } else {
                            seal.reject(ResponseError(
                                code: .invalidResponse,
                                reason: "Failed to retrieve the resource because the HTTP response body is empty"
                            ))
                        }
                    } else {
                        seal.reject(ResponseError(
                            code: .httpNotSuccessfulResponse,
                            reason: "Failed to retrieve the resource because the HTTP response status code is: \(response.statusCode)"
                        ))
                    }
                } else {
                    seal.reject(ResponseError(
                        code: .invalidResponse,
                        reason: "Expected the response to be a HTTP response, but got \(response?.className ?? "nil")"
                    ))
                }
            }
            .resume()
        }
    }

    private func getResourceUploadInfo(type: StorageResourceType, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<Response<[String: String]>> {
        return turmsClient.driver
            .send {
                $0.queryResourceUploadInfoRequest = .with {
                    $0.type = type
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.stringsWithVersion.strings.toMap()
                }
            }
    }

    private func queryResourceDownloadInfo(type: StorageResourceType, keyStr: String? = nil, keyNum: Int64? = nil) -> Promise<Response<[String: String]>> {
        return turmsClient.driver
            .send {
                $0.queryResourceDownloadInfoRequest = .with {
                    $0.type = type
                    if let v = keyStr {
                        $0.keyStr = v
                    }
                    if let v = keyNum {
                        $0.keyNum = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.stringsWithVersion.strings.toMap()
                }
            }
    }

    private func getBucketName(_ resourceType: StorageResourceType) -> String {
        return StorageService.RESOURCE_TYPE_TO_BUCKET_NAME[resourceType]!
    }

    private func getResourceUrl(_ data: [String: String], _ urlKeyName: String) throws -> String {
        let url = data[urlKeyName]
        if let uri = url {
            return uri
        }
        throw ResponseError(
            code: ResponseStatusCode.invalidResponse,
            reason: "Cannot get the resource URL because the key \"\(urlKeyName)\" doesn\'t exist"
        )
    }

    private func getAndRemoveResourceUrl(_ data: inout [String: String], _ urlKeyName: String) throws -> String {
        let url = data.removeValue(forKey: urlKeyName)
        if let uri = url {
            return uri
        }
        throw ResponseError(
            code: ResponseStatusCode.invalidResponse,
            reason: "Cannot get the resource URL because the key \"\(urlKeyName)\" doesn\'t exist"
        )
    }
}
