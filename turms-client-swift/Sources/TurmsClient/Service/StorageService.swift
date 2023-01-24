import Foundation
import PromiseKit

public class StorageService {
    private static let RESOURCE_ID_KEY_NAME = "id"
    private static let DEFAULT_URL_KEY_NAME = "url"
    private static let RESOURCE_TYPE_TO_BUCKET_NAME: [StorageResourceType: String] = Dictionary(uniqueKeysWithValues: StorageResourceType
        .allCases.map {
            ($0, "\($0)".camelCaseToSnakeCase)
        })
    private weak var turmsClient: TurmsClient!
    var serverUrl: String

    init(_ turmsClient: TurmsClient, storageServerUrl: String? = nil) {
        self.turmsClient = turmsClient
        serverUrl = storageServerUrl ?? "http://localhost:9000"
    }

    // User profile picture

    public func uploadUserProfilePicture(data: Data, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        if data.isEmpty {
            return Promise(error: ResponseError(
                code: .illegalArgument,
                reason: "The data of user profile picture must not be empty"
            ))
        }
        return queryUserProfilePictureUploadInfo(name: name, mediaType: mediaType, extra: extra)
            .then { uploadInfo -> Promise<Response<StorageUploadResult>> in
                var infoData = uploadInfo.data
                let url = try self.getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
                guard let id = id else {
                    throw ResponseError(
                        code: ResponseStatusCode.dataNotFound,
                        reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
                    )
                }
                return self.upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
            }
    }

    public func deleteUserProfilePicture(extra: [String: String]? = nil) -> Promise<Response<Void>> {
        return deleteResource(type: .userProfilePicture, extra: extra)
    }

    public func queryUserProfilePicture(userId: Int64, extra: [String: String]? = nil, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryUserProfilePictureDownloadInfo(userId: userId, extra: extra, fetch: fetchDownloadInfo, urlKeyName: urlKeyName)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryUserProfilePictureUploadInfo(name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceUploadInfo(type: .userProfilePicture, name: name, mediaType: mediaType, extra: extra)
    }

    public func queryUserProfilePictureDownloadInfo(userId: Int64, extra: [String: String]? = nil, fetch: Bool = false, urlKeyName: String? = nil) -> Promise<Response<[String: String]>> {
        if fetch {
            return queryResourceDownloadInfo(type: .userProfilePicture, idNum: userId, extra: extra)
        }
        let url = "\(serverUrl)/\(getBucketName(.userProfilePicture))/\(userId)"
        return Promise.value(Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ]))
    }

    // Group profile picture

    public func uploadGroupProfilePicture(groupId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, extra _: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
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
                let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
                guard let id = id else {
                    throw ResponseError(
                        code: ResponseStatusCode.dataNotFound,
                        reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
                    )
                }
                return self.upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
            }
    }

    public func deleteGroupProfilePicture(groupId: Int64, extra: [String: String]? = nil) -> Promise<Response<Void>> {
        return deleteResource(type: .groupProfilePicture, idNum: groupId, extra: extra)
    }

    public func queryGroupProfilePicture(groupId: Int64, extra: [String: String]? = nil, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryGroupProfilePictureDownloadInfo(groupId: groupId, extra: extra, fetch: fetchDownloadInfo, urlKeyName: urlKeyName)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryGroupProfilePictureUploadInfo(groupId: Int64, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceUploadInfo(type: .groupProfilePicture, idNum: groupId, name: name, mediaType: mediaType, extra: extra)
    }

    public func queryGroupProfilePictureDownloadInfo(groupId: Int64, extra: [String: String]? = nil, fetch: Bool = false, urlKeyName: String? = nil) -> Promise<Response<[String: String]>> {
        if fetch {
            return queryResourceDownloadInfo(type: .groupProfilePicture, idNum: groupId, extra: extra)
        }
        let url = "\(serverUrl)/\(getBucketName(.groupProfilePicture))/\(groupId)"
        return Promise.value(Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ]))
    }

    // Message attachment

    public func uploadMessageAttachment(data: Data, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        return uploadMessageAttachment0(data: data,
                                        name: name,
                                        mediaType: mediaType,
                                        extra: extra,
                                        urlKeyName: urlKeyName)
    }

    public func uploadMessageAttachmentInPrivateConversation(userId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        return uploadMessageAttachment0(data: data,
                                        userId: userId,
                                        name: name,
                                        mediaType: mediaType,
                                        extra: extra,
                                        urlKeyName: urlKeyName)
    }

    public func uploadMessageAttachmentInGroupConversation(groupId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        return uploadMessageAttachment0(data: data,
                                        groupId: groupId,
                                        name: name,
                                        mediaType: mediaType,
                                        extra: extra,
                                        urlKeyName: urlKeyName)
    }

    private func uploadMessageAttachment0(data: Data, userId: Int64? = nil, groupId: Int64? = nil, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil, urlKeyName: String? = nil) -> Promise<Response<StorageUploadResult>> {
        if data.isEmpty {
            return Promise(error: ResponseError(
                code: .illegalArgument,
                reason: "The data of message attachment must not be empty"
            ))
        }
        let queryUploadInfo: Promise<Response<[String: String]>>
        if userId == nil, groupId == nil {
            queryUploadInfo = queryMessageAttachmentUploadInfo(
                name: name, mediaType: mediaType, extra: extra
            )
        } else if userId != nil {
            if groupId != nil {
                return Promise(error: ResponseError(
                    code: .illegalArgument,
                    reason: "The user ID and the group ID must not both be non-null"
                ))
            }
            queryUploadInfo = queryMessageAttachmentUploadInfoInPrivateConversation(
                userId: userId!,
                name: name,
                mediaType: mediaType,
                extra: extra
            )
        } else {
            queryUploadInfo = queryMessageAttachmentUploadInfoInGroupConversation(
                groupId: groupId!,
                name: name,
                mediaType: mediaType,
                extra: extra
            )
        }
        return queryUploadInfo
            .then { uploadInfo -> Promise<Response<StorageUploadResult>> in
                var infoData = uploadInfo.data
                let url = try self.getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
                guard let id = id else {
                    throw ResponseError(
                        code: .dataNotFound,
                        reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
                    )
                }
                return self.upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
            }
    }

    public func deleteMessageAttachment(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, extra: [String: String]? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        return deleteResource(type: .messageAttachment, idNum: attachmentIdNum, idStr: attachmentIdStr, extra: extra)
    }

    public func shareMessageAttachmentWithUser(userId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        return turmsClient.driver
            .send {
                $0.updateMessageAttachmentInfoRequest = .with {
                    $0.userIDToShareWith = userId
                    if let v = attachmentIdNum {
                        $0.attachmentIDNum = v
                    }
                    if let v = attachmentIdStr {
                        $0.attachmentIDStr = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func shareMessageAttachmentWithGroup(groupId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        return turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.groupIDToShareWith = groupId
                if let v = attachmentIdNum {
                    $0.attachmentIDNum = v
                }
                if let v = attachmentIdStr {
                    $0.attachmentIDStr = v
                }
            }
        }
        .map {
            try $0.toResponse()
        }
    }

    public func unshareMessageAttachmentWithUser(userId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        return turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.userIDToUnshareWith = userId
                if let v = attachmentIdNum {
                    $0.attachmentIDNum = v
                }
                if let v = attachmentIdStr {
                    $0.attachmentIDStr = v
                }
            }
        }
        .map {
            try $0.toResponse()
        }
    }

    public func unshareMessageAttachmentWithGroup(groupId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        return turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.groupIDToUnshareWith = groupId
                if let v = attachmentIdNum {
                    $0.attachmentIDNum = v
                }
                if let v = attachmentIdStr {
                    $0.attachmentIDStr = v
                }
            }
        }
        .map {
            try $0.toResponse()
        }
    }

    public func queryMessageAttachment(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, extra: [String: String]? = nil, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil) -> Promise<Response<StorageResource>> {
        return queryMessageAttachmentDownloadInfo(attachmentIdNum: attachmentIdNum,
                                                  attachmentIdStr: attachmentIdStr,
                                                  extra: extra,
                                                  fetch: fetchDownloadInfo,
                                                  urlKeyName: urlKeyName)
            .then { downloadInfo -> Promise<Response<StorageResource>> in
                let url = try self.getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
                return self.getResource(url)
            }
    }

    public func queryMessageAttachmentUploadInfo(name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceUploadInfo(type: .messageAttachment, name: name, mediaType: mediaType, extra: extra)
    }

    public func queryMessageAttachmentUploadInfoInPrivateConversation(userId: Int64, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceUploadInfo(type: .messageAttachment, idNum: userId, name: name, mediaType: mediaType, extra: extra)
    }

    public func queryMessageAttachmentUploadInfoInGroupConversation(groupId: Int64, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return queryResourceUploadInfo(type: .messageAttachment, idNum: -groupId, name: name, mediaType: mediaType, extra: extra)
    }

    public func queryMessageAttachmentDownloadInfo(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, extra: [String: String]? = nil, fetch: Bool = false, urlKeyName: String? = nil) -> Promise<Response<[String: String]>> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            ))
        }
        if fetch {
            return queryResourceDownloadInfo(type: .messageAttachment, idNum: attachmentIdNum, idStr: attachmentIdStr, extra: extra)
        }
        let url = "\(serverUrl)/\(getBucketName(.messageAttachment))/\(attachmentIdNum?.description ?? attachmentIdStr!)"
        return Promise.value(Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ]))
    }

    public func queryMessageAttachmentInfosUploadedByMe(creationDateStart: Date? = nil, creationDateEnd: Date? = nil) -> Promise<Response<[StorageResourceInfo]>> {
        return turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                if let v = creationDateStart {
                    $0.creationDateStart = v.toMillis()
                }
                if let v = creationDateEnd {
                    $0.creationDateEnd = v.toMillis()
                }
            }
        }
        .map {
            try $0.toResponse {
                $0.storageResourceInfos.infos
            }
        }
    }

    public func queryMessageAttachmentInfosInPrivateConversations(userIds: Set<Int64>,
                                                                  areSharedByMe: Bool? = nil, creationDateStart: Date? = nil, creationDateEnd: Date? = nil) -> Promise<Response<[StorageResourceInfo]>>
    {
        return turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                $0.userIds = Array(userIds)
                if let v = areSharedByMe {
                    $0.areSharedByMe = v
                }
                if let v = creationDateStart {
                    $0.creationDateStart = v.toMillis()
                }
                if let v = creationDateEnd {
                    $0.creationDateEnd = v.toMillis()
                }
            }
        }
        .map {
            try $0.toResponse {
                $0.storageResourceInfos.infos
            }
        }
    }

    public func queryMessageAttachmentInfosInGroupConversations(groupIds: Set<Int64>,
                                                                userIds: Set<Int64>? = nil, creationDateStart: Date? = nil, creationDateEnd: Date? = nil) -> Promise<Response<[StorageResourceInfo]>>
    {
        return turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                $0.groupIds = Array(groupIds)
                if let v = userIds {
                    $0.userIds = Array(v)
                }
                if let v = creationDateStart {
                    $0.creationDateStart = v.toMillis()
                }
                if let v = creationDateEnd {
                    $0.creationDateEnd = v.toMillis()
                }
            }
        }
        .map {
            try $0.toResponse {
                $0.storageResourceInfos.infos
            }
        }
    }

    // Base

    private func upload(url: String, formData: [String: String], data: Data, id: String, name: String? = nil, mediaType: String? = nil) -> Promise<Response<StorageUploadResult>> {
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
            request.addTextField(named: "key", value: id)
            if let mediaType {
                request.addTextField(named: "Content-Type", value: mediaType)
            }
            request.addDataField(fieldName: "file", fileName: name ?? id, data: data, mediaType: mediaType)

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
                        let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map {
                            (String(describing: $0.key), String(describing: $0.value))
                        })
                        let idNum = Int64(id)
                        let idStr = idNum == nil ? id : nil
                        seal.fulfill(Response.value(StorageUploadResult(uri: httpUrl, metadata: headers, data: text, resourceIdNum: idNum, resourceIdStr: idStr)))
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
            })
            .resume()
        }
    }

    private func deleteResource(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, extra: [String: String]? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteResourceRequest = .with {
                    $0.type = type
                    if let v = idNum {
                        $0.idNum = v
                    }
                    if let v = idStr {
                        $0.idStr = v
                    }
                    if let v = extra {
                        $0.extra = v
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
                        let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map {
                            (String(describing: $0.key), String(describing: $0.value))
                        })
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

    private func queryResourceUploadInfo(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, name: String? = nil, mediaType: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return turmsClient.driver
            .send {
                $0.queryResourceUploadInfoRequest = .with {
                    $0.type = type
                    if let v = idNum {
                        $0.idNum = v
                    }
                    if let v = idStr {
                        $0.idStr = v
                    }
                    if let v = name {
                        $0.name = v
                    }
                    if let v = mediaType {
                        $0.mediaType = v
                    }
                    if let v = extra {
                        $0.extra = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.stringsWithVersion.strings.toMap()
                }
            }
    }

    private func queryResourceDownloadInfo(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, extra: [String: String]? = nil) -> Promise<Response<[String: String]>> {
        return turmsClient.driver
            .send {
                $0.queryResourceDownloadInfoRequest = .with {
                    $0.type = type
                    if let v = idNum {
                        $0.idNum = v
                    }
                    if let v = idStr {
                        $0.idStr = v
                    }
                    if let v = extra {
                        $0.extra = v
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
        if let url = url {
            return url
        }
        throw ResponseError(
            code: ResponseStatusCode.dataNotFound,
            reason: "Could not get the resource URL because the key \"\(urlKeyName)\" does not exist in the data: \(data)"
        )
    }

    private func getAndRemoveResourceUrl(_ data: inout [String: String], _ urlKeyName: String) throws -> String {
        let url = data.removeValue(forKey: urlKeyName)
        if let url = url {
            return url
        }
        throw ResponseError(
            code: ResponseStatusCode.dataNotFound,
            reason: "Could not get the resource URL because the key \"\(urlKeyName)\" does not exist in the data: \(data)"
        )
    }
}
