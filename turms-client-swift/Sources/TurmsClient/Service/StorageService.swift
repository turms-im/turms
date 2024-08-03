import Foundation

public class StorageService {
    private static let RESOURCE_ID_KEY_NAME = "id"
    private static let DEFAULT_URL_KEY_NAME = "url"
    private static let RESOURCE_TYPE_TO_BUCKET_NAME: [StorageResourceType: String] = Dictionary(uniqueKeysWithValues: StorageResourceType
        .allCases.map {
            ($0, "\($0)".camelCaseToSnakeCase)
        })
    private unowned var turmsClient: TurmsClient
    var serverUrl: String

    init(_ turmsClient: TurmsClient, storageServerUrl: String? = nil) {
        self.turmsClient = turmsClient
        serverUrl = storageServerUrl ?? "http://localhost:9000"
    }

    // User profile picture

    public func uploadUserProfilePicture(data: Data, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        if data.isEmpty {
            throw ResponseError(
                code: .illegalArgument,
                reason: "The data of user profile picture must not be empty"
            )
        }
        let uploadInfo = try await queryUserProfilePictureUploadInfo(name: name, mediaType: mediaType, customAttributes: customAttributes)

        var infoData = uploadInfo.data
        let url = try getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
        guard let id else {
            throw ResponseError(
                code: ResponseStatusCode.dataNotFound,
                reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
            )
        }
        return try await upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
    }

    public func deleteUserProfilePicture(customAttributes: [Value]? = nil) async throws -> Response<Void> {
        return try await deleteResource(type: .userProfilePicture, customAttributes: customAttributes)
    }

    public func queryUserProfilePicture(userId: Int64, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageResource> {
        let downloadInfo = try await queryUserProfilePictureDownloadInfo(userId: userId, fetch: fetchDownloadInfo, urlKeyName: urlKeyName, customAttributes: customAttributes)
        let url = try getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        return try await getResource(url)
    }

    public func queryUserProfilePictureUploadInfo(name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try await queryResourceUploadInfo(type: .userProfilePicture, name: name, mediaType: mediaType, customAttributes: customAttributes)
    }

    public func queryUserProfilePictureDownloadInfo(userId: Int64, fetch: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        if fetch {
            return try await queryResourceDownloadInfo(type: .userProfilePicture, idNum: userId, customAttributes: customAttributes)
        }
        let url = "\(serverUrl)/\(getBucketName(.userProfilePicture))/\(userId)"
        return Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ])
    }

    // Group profile picture

    public func uploadGroupProfilePicture(groupId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes _: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        if data.isEmpty {
            throw ResponseError(
                code: .illegalArgument,
                reason: "The data of group profile picture must not be empty"
            )
        }
        let uploadInfo = try await queryGroupProfilePictureUploadInfo(groupId: groupId)
        var infoData = uploadInfo.data
        let url = try getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
        guard let id else {
            throw ResponseError(
                code: ResponseStatusCode.dataNotFound,
                reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
            )
        }
        return try await upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
    }

    public func deleteGroupProfilePicture(groupId: Int64, customAttributes: [Value]? = nil) async throws -> Response<Void> {
        return try await deleteResource(type: .groupProfilePicture, idNum: groupId, customAttributes: customAttributes)
    }

    public func queryGroupProfilePicture(groupId: Int64, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageResource> {
        let downloadInfo = try await queryGroupProfilePictureDownloadInfo(groupId: groupId, fetch: fetchDownloadInfo, urlKeyName: urlKeyName, customAttributes: customAttributes)

        let url = try getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        return try await getResource(url)
    }

    public func queryGroupProfilePictureUploadInfo(groupId: Int64, name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try await queryResourceUploadInfo(type: .groupProfilePicture, idNum: groupId, name: name, mediaType: mediaType, customAttributes: customAttributes)
    }

    public func queryGroupProfilePictureDownloadInfo(groupId: Int64, fetch: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        if fetch {
            return try await queryResourceDownloadInfo(type: .groupProfilePicture, idNum: groupId, customAttributes: customAttributes)
        }
        let url = "\(serverUrl)/\(getBucketName(.groupProfilePicture))/\(groupId)"
        return Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ])
    }

    // Message attachment

    public func uploadMessageAttachment(data: Data, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        return try await uploadMessageAttachment0(data: data,
                                                  name: name,
                                                  mediaType: mediaType,
                                                  urlKeyName: urlKeyName,
                                                  customAttributes: customAttributes)
    }

    public func uploadMessageAttachmentInPrivateConversation(userId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        return try await uploadMessageAttachment0(data: data,
                                                  userId: userId,
                                                  name: name,
                                                  mediaType: mediaType,
                                                  urlKeyName: urlKeyName,
                                                  customAttributes: customAttributes)
    }

    public func uploadMessageAttachmentInGroupConversation(groupId: Int64, data: Data, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        return try await uploadMessageAttachment0(data: data,
                                                  groupId: groupId,
                                                  name: name,
                                                  mediaType: mediaType,
                                                  urlKeyName: urlKeyName,
                                                  customAttributes: customAttributes)
    }

    private func uploadMessageAttachment0(data: Data, userId: Int64? = nil, groupId: Int64? = nil, name: String? = nil, mediaType: String? = nil, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageUploadResult> {
        if data.isEmpty {
            throw ResponseError(
                code: .illegalArgument,
                reason: "The data of message attachment must not be empty"
            )
        }
        let uploadInfo = if userId == nil, groupId == nil {
            try await queryMessageAttachmentUploadInfo(
                name: name, mediaType: mediaType, customAttributes: customAttributes
            )
        } else if userId != nil {
            if groupId != nil {
                throw ResponseError(
                    code: .illegalArgument,
                    reason: "The user ID and the group ID must not both be non-null"
                )
            } else {
                try await queryMessageAttachmentUploadInfoInPrivateConversation(
                    userId: userId!,
                    name: name,
                    mediaType: mediaType,
                    customAttributes: customAttributes
                )
            }
        } else {
            try await queryMessageAttachmentUploadInfoInGroupConversation(
                groupId: groupId!,
                name: name,
                mediaType: mediaType,
                customAttributes: customAttributes
            )
        }

        var infoData = uploadInfo.data
        let url = try getAndRemoveResourceUrl(&infoData, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        let id = infoData.removeValue(forKey: StorageService.RESOURCE_ID_KEY_NAME)
        guard let id else {
            throw ResponseError(
                code: .dataNotFound,
                reason: "Could not get the resource ID because the key \"\(StorageService.RESOURCE_ID_KEY_NAME)\" does not exist in the data: \(uploadInfo.data)"
            )
        }
        return try await upload(url: url, formData: infoData, data: data, id: id, name: name, mediaType: mediaType)
    }

    public func deleteMessageAttachment(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<Void> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        return try await deleteResource(type: .messageAttachment, idNum: attachmentIdNum, idStr: attachmentIdStr, customAttributes: customAttributes)
    }

    public func shareMessageAttachmentWithUser(userId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) async throws -> Response<Void> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        return try (await turmsClient.driver
            .send {
                $0.updateMessageAttachmentInfoRequest = .with {
                    $0.userIDToShareWith = userId
                    if let attachmentIdNum {
                        $0.attachmentIDNum = attachmentIdNum
                    }
                    if let attachmentIdStr {
                        $0.attachmentIDStr = attachmentIdStr
                    }
                }
            }
        ).toResponse()
    }

    public func shareMessageAttachmentWithGroup(groupId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) async throws -> Response<Void> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        return try (await turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.groupIDToShareWith = groupId
                if let attachmentIdNum {
                    $0.attachmentIDNum = attachmentIdNum
                }
                if let attachmentIdStr {
                    $0.attachmentIDStr = attachmentIdStr
                }
            }
        }
        ).toResponse()
    }

    public func unshareMessageAttachmentWithUser(userId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) async throws -> Response<Void> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        return try (await turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.userIDToUnshareWith = userId
                if let attachmentIdNum {
                    $0.attachmentIDNum = attachmentIdNum
                }
                if let attachmentIdStr {
                    $0.attachmentIDStr = attachmentIdStr
                }
            }
        }
        ).toResponse()
    }

    public func unshareMessageAttachmentWithGroup(groupId: Int64, attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil) async throws -> Response<Void> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        return try (await turmsClient.driver.send {
            $0.updateMessageAttachmentInfoRequest = .with {
                $0.groupIDToUnshareWith = groupId
                if let attachmentIdNum {
                    $0.attachmentIDNum = attachmentIdNum
                }
                if let attachmentIdStr {
                    $0.attachmentIDStr = attachmentIdStr
                }
            }
        }
        ).toResponse()
    }

    public func queryMessageAttachment(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, fetchDownloadInfo: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<StorageResource> {
        let downloadInfo = try await queryMessageAttachmentDownloadInfo(attachmentIdNum: attachmentIdNum,
                                                                        attachmentIdStr: attachmentIdStr,
                                                                        fetch: fetchDownloadInfo,
                                                                        urlKeyName: urlKeyName,
                                                                        customAttributes: customAttributes)

        let url = try getResourceUrl(downloadInfo.data, urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME)
        return try await getResource(url)
    }

    public func queryMessageAttachmentUploadInfo(name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try await queryResourceUploadInfo(type: .messageAttachment, name: name, mediaType: mediaType, customAttributes: customAttributes)
    }

    public func queryMessageAttachmentUploadInfoInPrivateConversation(userId: Int64, name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try await queryResourceUploadInfo(type: .messageAttachment, idNum: userId, name: name, mediaType: mediaType, customAttributes: customAttributes)
    }

    public func queryMessageAttachmentUploadInfoInGroupConversation(groupId: Int64, name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try await queryResourceUploadInfo(type: .messageAttachment, idNum: -groupId, name: name, mediaType: mediaType, customAttributes: customAttributes)
    }

    public func queryMessageAttachmentDownloadInfo(attachmentIdNum: Int64? = nil, attachmentIdStr: String? = nil, fetch: Bool = false, urlKeyName: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        if Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr) {
            throw ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "One and only one attachment ID must be specified"
            )
        }
        if fetch {
            return try await queryResourceDownloadInfo(type: .messageAttachment, idNum: attachmentIdNum, idStr: attachmentIdStr, customAttributes: customAttributes)
        }
        let url = "\(serverUrl)/\(getBucketName(.messageAttachment))/\(attachmentIdNum?.description ?? attachmentIdStr!)"
        return Response.value([
            urlKeyName ?? StorageService.DEFAULT_URL_KEY_NAME: url,
        ])
    }

    public func queryMessageAttachmentInfosUploadedByMe(creationDateStart: Date? = nil, creationDateEnd: Date? = nil) async throws -> Response<[StorageResourceInfo]> {
        return try (await turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                if let creationDateStart {
                    $0.creationDateStart = creationDateStart.toMillis()
                }
                if let creationDateEnd {
                    $0.creationDateEnd = creationDateEnd.toMillis()
                }
            }
        }).toResponse {
            $0.storageResourceInfos.infos
        }
    }

    public func queryMessageAttachmentInfosInPrivateConversations(userIds: Set<Int64>,
                                                                  areSharedByMe: Bool? = nil, creationDateStart: Date? = nil, creationDateEnd: Date? = nil) async throws -> Response<[StorageResourceInfo]>
    {
        return try (await turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                $0.userIds = Array(userIds)
                if let areSharedByMe {
                    $0.areSharedByMe = areSharedByMe
                }
                if let creationDateStart {
                    $0.creationDateStart = creationDateStart.toMillis()
                }
                if let creationDateEnd {
                    $0.creationDateEnd = creationDateEnd.toMillis()
                }
            }
        }).toResponse {
            $0.storageResourceInfos.infos
        }
    }

    public func queryMessageAttachmentInfosInGroupConversations(groupIds: Set<Int64>,
                                                                userIds: Set<Int64>? = nil, creationDateStart: Date? = nil, creationDateEnd: Date? = nil) async throws -> Response<[StorageResourceInfo]>
    {
        return try (await turmsClient.driver.send {
            $0.queryMessageAttachmentInfosRequest = .with {
                $0.groupIds = Array(groupIds)
                if let userIds {
                    $0.userIds = Array(userIds)
                }
                if let creationDateStart {
                    $0.creationDateStart = creationDateStart.toMillis()
                }
                if let creationDateEnd {
                    $0.creationDateEnd = creationDateEnd.toMillis()
                }
            }
        }).toResponse {
            $0.storageResourceInfos.infos
        }
    }

    // Base

    private func upload(url: String, formData: [String: String], data: Data, id: String, name: String? = nil, mediaType: String? = nil) async throws -> Response<StorageUploadResult> {
        if data.isEmpty {
            throw ResponseError(code: .illegalArgument, reason: "The data of resource must not be empty")
        }
        let httpUrl = URL(string: url)
        guard let httpUrl else {
            throw ResponseError(code: .illegalArgument, reason: "The URL is illegal: \(url)")
        }
        let request = MultipartFormDataRequest(url: httpUrl)
        for (key, value) in formData {
            request.addTextField(named: key, value: value)
        }
        request.addTextField(named: "key", value: id)
        if let mediaType {
            request.addTextField(named: "Content-Type", value: mediaType)
        }
        request.addDataField(fieldName: "file", fileName: name ?? id, data: data, mediaType: mediaType)

        var responseData: Data
        var response: URLResponse
        do {
            (responseData, response) = try await URLSession.shared.data(for: request.asURLRequest())
        } catch {
            throw ResponseError(
                code: .httpError,
                reason: "Caught an error while sending an HTTP POST request to update the resource",
                cause: error
            )
        }
        if let response = response as? HTTPURLResponse {
            if response.isSuccessful {
                let text = String(decoding: responseData, as: UTF8.self)
                let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map {
                    (String(describing: $0.key), String(describing: $0.value))
                })
                let idNum = Int64(id)
                let idStr = idNum == nil ? id : nil
                return Response.value(StorageUploadResult(uri: httpUrl, metadata: headers, data: text, resourceIdNum: idNum, resourceIdStr: idStr))
            } else {
                throw ResponseError(
                    code: .httpNotSuccessfulResponse,
                    reason: "Failed to upload the resource because the HTTP response status code is: \(response.statusCode)"
                )
            }
        } else {
            throw ResponseError(
                code: .invalidResponse,
                reason: "Expected the response to be a HTTP response, but got \(response.className)"
            )
        }
    }

    private func deleteResource(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<Void> {
        return try (await turmsClient.driver
            .send {
                $0.deleteResourceRequest = .with {
                    $0.type = type
                    if let idNum {
                        $0.idNum = idNum
                    }
                    if let idStr {
                        $0.idStr = idStr
                    }
                    if let customAttributes {
                        $0.customAttributes = customAttributes
                    }
                }
            }
        ).toResponse()
    }

    private func getResource(_ url: String) async throws -> Response<StorageResource> {
        let reqUrl = URL(string: url)
        guard let reqUrl else {
            throw ResponseError(
                code: .illegalArgument,
                reason: "The URL is illegal: \(url)"
            )
        }
        var request = URLRequest(url: reqUrl)
        request.httpMethod = "GET"

        var responseData: Data
        var response: URLResponse
        do {
            (responseData, response) = try await URLSession.shared.data(for: request)
        } catch {
            throw ResponseError(
                code: .httpError,
                reason: "Caught an error while sending an HTTP GET request to retrieve the resource",
                cause: error
            )
        }
        if let response = response as? HTTPURLResponse {
            if response.isSuccessful {
                let headers = Dictionary(uniqueKeysWithValues: response.allHeaderFields.map {
                    (String(describing: $0.key), String(describing: $0.value))
                })
                return Response.value(StorageResource(uri: reqUrl, metadata: headers, data: responseData))
            } else {
                throw ResponseError(
                    code: .httpNotSuccessfulResponse,
                    reason: "Failed to retrieve the resource because the HTTP response status code is: \(response.statusCode)"
                )
            }
        } else {
            throw ResponseError(
                code: .invalidResponse,
                reason: "Expected the response to be a HTTP response, but got \(response.className)"
            )
        }
    }

    private func queryResourceUploadInfo(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, name: String? = nil, mediaType: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try (await turmsClient.driver
            .send {
                $0.queryResourceUploadInfoRequest = .with {
                    $0.type = type
                    if let idNum {
                        $0.idNum = idNum
                    }
                    if let idStr {
                        $0.idStr = idStr
                    }
                    if let name {
                        $0.name = name
                    }
                    if let mediaType {
                        $0.mediaType = mediaType
                    }
                    if let customAttributes {
                        $0.customAttributes = customAttributes
                    }
                }
            }).toResponse {
            try $0.stringsWithVersion.strings.toMap()
        }
    }

    private func queryResourceDownloadInfo(type: StorageResourceType, idNum: Int64? = nil, idStr: String? = nil, customAttributes: [Value]? = nil) async throws -> Response<[String: String]> {
        return try (await turmsClient.driver
            .send {
                $0.queryResourceDownloadInfoRequest = .with {
                    $0.type = type
                    if let idNum {
                        $0.idNum = idNum
                    }
                    if let idStr {
                        $0.idStr = idStr
                    }
                    if let customAttributes {
                        $0.customAttributes = customAttributes
                    }
                }
            }).toResponse {
            try $0.stringsWithVersion.strings.toMap()
        }
    }

    private func getBucketName(_ resourceType: StorageResourceType) -> String {
        return StorageService.RESOURCE_TYPE_TO_BUCKET_NAME[resourceType]!
    }

    private func getResourceUrl(_ data: [String: String], _ urlKeyName: String) throws -> String {
        let url = data[urlKeyName]
        if let url {
            return url
        }
        throw ResponseError(
            code: ResponseStatusCode.dataNotFound,
            reason: "Could not get the resource URL because the key \"\(urlKeyName)\" does not exist in the data: \(data)"
        )
    }

    private func getAndRemoveResourceUrl(_ data: inout [String: String], _ urlKeyName: String) throws -> String {
        let url = data.removeValue(forKey: urlKeyName)
        if let url {
            return url
        }
        throw ResponseError(
            code: ResponseStatusCode.dataNotFound,
            reason: "Could not get the resource URL because the key \"\(urlKeyName)\" does not exist in the data: \(data)"
        )
    }
}
