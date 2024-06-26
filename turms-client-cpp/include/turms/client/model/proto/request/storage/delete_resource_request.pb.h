// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/storage/delete_resource_request.proto
// Protobuf C++ Version: 5.26.1

#ifndef GOOGLE_PROTOBUF_INCLUDED_request_2fstorage_2fdelete_5fresource_5frequest_2eproto_2epb_2eh
#define GOOGLE_PROTOBUF_INCLUDED_request_2fstorage_2fdelete_5fresource_5frequest_2eproto_2epb_2eh

#include <limits>
#include <string>
#include <type_traits>
#include <utility>

#include "google/protobuf/port_def.inc"
#if PROTOBUF_VERSION != 5026001
#error "Protobuf C++ gencode is built with an incompatible version of"
#error "Protobuf C++ headers/runtime. See"
#error "https://protobuf.dev/support/cross-version-runtime-guarantee/#cpp"
#endif
#include "google/protobuf/arena.h"
#include "google/protobuf/arenastring.h"
#include "google/protobuf/extension_set.h"  // IWYU pragma: export
#include "google/protobuf/generated_message_tctable_decl.h"
#include "google/protobuf/generated_message_util.h"
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/map.h"  // IWYU pragma: export
#include "google/protobuf/map_field_lite.h"
#include "google/protobuf/message_lite.h"
#include "google/protobuf/metadata_lite.h"
#include "google/protobuf/port_undef.inc"
#include "google/protobuf/repeated_field.h"  // IWYU pragma: export
#include "turms/client/model/proto/constant/storage_resource_type.pb.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"

#define PROTOBUF_INTERNAL_EXPORT_request_2fstorage_2fdelete_5fresource_5frequest_2eproto

namespace google {
namespace protobuf {
namespace internal {
class AnyMetadata;
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_request_2fstorage_2fdelete_5fresource_5frequest_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class DeleteResourceRequest;
struct DeleteResourceRequestDefaultTypeInternal;
extern DeleteResourceRequestDefaultTypeInternal _DeleteResourceRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace google {
namespace protobuf {}  // namespace protobuf
}  // namespace google

namespace turms {
namespace client {
namespace model {
namespace proto {

// ===================================================================

// -------------------------------------------------------------------

// -------------------------------------------------------------------

class DeleteResourceRequest final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.DeleteResourceRequest) */ {
   public:
    inline DeleteResourceRequest()
        : DeleteResourceRequest(nullptr) {
    }
    ~DeleteResourceRequest() override;
    template <typename = void>
    explicit PROTOBUF_CONSTEXPR DeleteResourceRequest(
        ::google::protobuf::internal::ConstantInitialized);

    inline DeleteResourceRequest(const DeleteResourceRequest& from)
        : DeleteResourceRequest(nullptr, from) {
    }
    inline DeleteResourceRequest(DeleteResourceRequest&& from) noexcept
        : DeleteResourceRequest(nullptr, std::move(from)) {
    }
    inline DeleteResourceRequest& operator=(const DeleteResourceRequest& from) {
        CopyFrom(from);
        return *this;
    }
    inline DeleteResourceRequest& operator=(DeleteResourceRequest&& from) noexcept {
        if (this == &from)
            return *this;
        if (GetArena() == from.GetArena()
#ifdef PROTOBUF_FORCE_COPY_IN_MOVE
            && GetArena() != nullptr
#endif  // !PROTOBUF_FORCE_COPY_IN_MOVE
        ) {
            InternalSwap(&from);
        } else {
            CopyFrom(from);
        }
        return *this;
    }

    inline const std::string& unknown_fields() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
        return _internal_metadata_.unknown_fields<std::string>(
            ::google::protobuf::internal::GetEmptyString);
    }
    inline std::string* mutable_unknown_fields() ABSL_ATTRIBUTE_LIFETIME_BOUND {
        return _internal_metadata_.mutable_unknown_fields<std::string>();
    }

    static const DeleteResourceRequest& default_instance() {
        return *internal_default_instance();
    }
    static inline const DeleteResourceRequest* internal_default_instance() {
        return reinterpret_cast<const DeleteResourceRequest*>(
            &_DeleteResourceRequest_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 1;
    friend void swap(DeleteResourceRequest& a, DeleteResourceRequest& b) {
        a.Swap(&b);
    }
    inline void Swap(DeleteResourceRequest* other) {
        if (other == this)
            return;
#ifdef PROTOBUF_FORCE_COPY_IN_SWAP
        if (GetArena() != nullptr && GetArena() == other->GetArena()) {
#else   // PROTOBUF_FORCE_COPY_IN_SWAP
        if (GetArena() == other->GetArena()) {
#endif  // !PROTOBUF_FORCE_COPY_IN_SWAP
            InternalSwap(other);
        } else {
            ::google::protobuf::internal::GenericSwap(this, other);
        }
    }
    void UnsafeArenaSwap(DeleteResourceRequest* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    DeleteResourceRequest* New(::google::protobuf::Arena* arena = nullptr) const final {
        return ::google::protobuf::MessageLite::DefaultConstruct<DeleteResourceRequest>(arena);
    }
    void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) final;
    void CopyFrom(const DeleteResourceRequest& from);
    void MergeFrom(const DeleteResourceRequest& from);
    ABSL_ATTRIBUTE_REINITIALIZES void Clear() final;
    bool IsInitialized() const final;

    ::size_t ByteSizeLong() const final;
    const char* _InternalParse(const char* ptr,
                               ::google::protobuf::internal::ParseContext* ctx) final;
    ::uint8_t* _InternalSerialize(::uint8_t* target,
                                  ::google::protobuf::io::EpsCopyOutputStream* stream) const final;
    int GetCachedSize() const {
        return _impl_._cached_size_.Get();
    }

   private:
    void SharedCtor(::google::protobuf::Arena* arena);
    void SharedDtor();
    void InternalSwap(DeleteResourceRequest* other);

   private:
    friend class ::google::protobuf::internal::AnyMetadata;
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.DeleteResourceRequest";
    }

   protected:
    explicit DeleteResourceRequest(::google::protobuf::Arena* arena);
    DeleteResourceRequest(::google::protobuf::Arena* arena, const DeleteResourceRequest& from);
    DeleteResourceRequest(::google::protobuf::Arena* arena, DeleteResourceRequest&& from) noexcept
        : DeleteResourceRequest(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::MessageLite::ClassData* GetClassData() const final;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kExtraFieldNumber = 4,
        kIdStrFieldNumber = 3,
        kIdNumFieldNumber = 2,
        kTypeFieldNumber = 1,
    };
    // map<string, string> extra = 4;
    int extra_size() const;

   private:
    int _internal_extra_size() const;

   public:
    void clear_extra();
    const ::google::protobuf::Map<std::string, std::string>& extra() const;
    ::google::protobuf::Map<std::string, std::string>* mutable_extra();

   private:
    const ::google::protobuf::Map<std::string, std::string>& _internal_extra() const;
    ::google::protobuf::Map<std::string, std::string>* _internal_mutable_extra();

   public:
    // optional string id_str = 3;
    bool has_id_str() const;
    void clear_id_str();
    const std::string& id_str() const;
    template <typename Arg_ = const std::string&, typename... Args_>
    void set_id_str(Arg_&& arg, Args_... args);
    std::string* mutable_id_str();
    PROTOBUF_NODISCARD std::string* release_id_str();
    void set_allocated_id_str(std::string* value);

   private:
    const std::string& _internal_id_str() const;
    inline PROTOBUF_ALWAYS_INLINE void _internal_set_id_str(const std::string& value);
    std::string* _internal_mutable_id_str();

   public:
    // optional int64 id_num = 2;
    bool has_id_num() const;
    void clear_id_num();
    ::int64_t id_num() const;
    void set_id_num(::int64_t value);

   private:
    ::int64_t _internal_id_num() const;
    void _internal_set_id_num(::int64_t value);

   public:
    // .turms.client.model.proto.StorageResourceType type = 1;
    void clear_type();
    ::turms::client::model::proto::StorageResourceType type() const;
    void set_type(::turms::client::model::proto::StorageResourceType value);

   private:
    ::turms::client::model::proto::StorageResourceType _internal_type() const;
    void _internal_set_type(::turms::client::model::proto::StorageResourceType value);

   public:
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.DeleteResourceRequest)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<2, 4, 1, 66, 2> _table_;
    friend class ::google::protobuf::MessageLite;
    friend class ::google::protobuf::Arena;
    template <typename T>
    friend class ::google::protobuf::Arena::InternalHelper;
    using InternalArenaConstructable_ = void;
    using DestructorSkippable_ = void;
    struct Impl_ {
        inline explicit constexpr Impl_(::google::protobuf::internal::ConstantInitialized) noexcept;
        inline explicit Impl_(::google::protobuf::internal::InternalVisibility visibility,
                              ::google::protobuf::Arena* arena);
        inline explicit Impl_(::google::protobuf::internal::InternalVisibility visibility,
                              ::google::protobuf::Arena* arena,
                              const Impl_& from);
        ::google::protobuf::internal::HasBits<1> _has_bits_;
        mutable ::google::protobuf::internal::CachedSize _cached_size_;
        ::google::protobuf::internal::MapFieldLite<std::string, std::string> extra_;
        ::google::protobuf::internal::ArenaStringPtr id_str_;
        ::int64_t id_num_;
        int type_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_request_2fstorage_2fdelete_5fresource_5frequest_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// -------------------------------------------------------------------

// DeleteResourceRequest

// .turms.client.model.proto.StorageResourceType type = 1;
inline void DeleteResourceRequest::clear_type() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.type_ = 0;
}
inline ::turms::client::model::proto::StorageResourceType DeleteResourceRequest::type() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.DeleteResourceRequest.type)
    return _internal_type();
}
inline void DeleteResourceRequest::set_type(
    ::turms::client::model::proto::StorageResourceType value) {
    _internal_set_type(value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.DeleteResourceRequest.type)
}
inline ::turms::client::model::proto::StorageResourceType DeleteResourceRequest::_internal_type()
    const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return static_cast<::turms::client::model::proto::StorageResourceType>(_impl_.type_);
}
inline void DeleteResourceRequest::_internal_set_type(
    ::turms::client::model::proto::StorageResourceType value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.type_ = value;
}

// optional int64 id_num = 2;
inline bool DeleteResourceRequest::has_id_num() const {
    bool value = (_impl_._has_bits_[0] & 0x00000002u) != 0;
    return value;
}
inline void DeleteResourceRequest::clear_id_num() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.id_num_ = ::int64_t{0};
    _impl_._has_bits_[0] &= ~0x00000002u;
}
inline ::int64_t DeleteResourceRequest::id_num() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.DeleteResourceRequest.id_num)
    return _internal_id_num();
}
inline void DeleteResourceRequest::set_id_num(::int64_t value) {
    _internal_set_id_num(value);
    _impl_._has_bits_[0] |= 0x00000002u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.DeleteResourceRequest.id_num)
}
inline ::int64_t DeleteResourceRequest::_internal_id_num() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.id_num_;
}
inline void DeleteResourceRequest::_internal_set_id_num(::int64_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.id_num_ = value;
}

// optional string id_str = 3;
inline bool DeleteResourceRequest::has_id_str() const {
    bool value = (_impl_._has_bits_[0] & 0x00000001u) != 0;
    return value;
}
inline void DeleteResourceRequest::clear_id_str() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.id_str_.ClearToEmpty();
    _impl_._has_bits_[0] &= ~0x00000001u;
}
inline const std::string& DeleteResourceRequest::id_str() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.DeleteResourceRequest.id_str)
    return _internal_id_str();
}
template <typename Arg_, typename... Args_>
inline PROTOBUF_ALWAYS_INLINE void DeleteResourceRequest::set_id_str(Arg_&& arg, Args_... args) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_._has_bits_[0] |= 0x00000001u;
    _impl_.id_str_.Set(static_cast<Arg_&&>(arg), args..., GetArena());
    // @@protoc_insertion_point(field_set:turms.client.model.proto.DeleteResourceRequest.id_str)
}
inline std::string* DeleteResourceRequest::mutable_id_str() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    std::string* _s = _internal_mutable_id_str();
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.DeleteResourceRequest.id_str)
    return _s;
}
inline const std::string& DeleteResourceRequest::_internal_id_str() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.id_str_.Get();
}
inline void DeleteResourceRequest::_internal_set_id_str(const std::string& value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_._has_bits_[0] |= 0x00000001u;
    _impl_.id_str_.Set(value, GetArena());
}
inline std::string* DeleteResourceRequest::_internal_mutable_id_str() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_._has_bits_[0] |= 0x00000001u;
    return _impl_.id_str_.Mutable(GetArena());
}
inline std::string* DeleteResourceRequest::release_id_str() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    // @@protoc_insertion_point(field_release:turms.client.model.proto.DeleteResourceRequest.id_str)
    if ((_impl_._has_bits_[0] & 0x00000001u) == 0) {
        return nullptr;
    }
    _impl_._has_bits_[0] &= ~0x00000001u;
    auto* released = _impl_.id_str_.Release();
#ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.id_str_.Set("", GetArena());
#endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
    return released;
}
inline void DeleteResourceRequest::set_allocated_id_str(std::string* value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    if (value != nullptr) {
        _impl_._has_bits_[0] |= 0x00000001u;
    } else {
        _impl_._has_bits_[0] &= ~0x00000001u;
    }
    _impl_.id_str_.SetAllocated(value, GetArena());
#ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    if (_impl_.id_str_.IsDefault()) {
        _impl_.id_str_.Set("", GetArena());
    }
#endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
    // @@protoc_insertion_point(field_set_allocated:turms.client.model.proto.DeleteResourceRequest.id_str)
}

// map<string, string> extra = 4;
inline int DeleteResourceRequest::_internal_extra_size() const {
    return _internal_extra().size();
}
inline int DeleteResourceRequest::extra_size() const {
    return _internal_extra_size();
}
inline void DeleteResourceRequest::clear_extra() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.extra_.Clear();
}
inline const ::google::protobuf::Map<std::string, std::string>&
DeleteResourceRequest::_internal_extra() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.extra_.GetMap();
}
inline const ::google::protobuf::Map<std::string, std::string>& DeleteResourceRequest::extra() const
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_map:turms.client.model.proto.DeleteResourceRequest.extra)
    return _internal_extra();
}
inline ::google::protobuf::Map<std::string, std::string>*
DeleteResourceRequest::_internal_mutable_extra() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    return _impl_.extra_.MutableMap();
}
inline ::google::protobuf::Map<std::string, std::string>* DeleteResourceRequest::mutable_extra()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_map:turms.client.model.proto.DeleteResourceRequest.extra)
    return _internal_mutable_extra();
}

#ifdef __GNUC__
#pragma GCC diagnostic pop
#endif  // __GNUC__

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms

// @@protoc_insertion_point(global_scope)

#include "google/protobuf/port_undef.inc"

#endif  // GOOGLE_PROTOBUF_INCLUDED_request_2fstorage_2fdelete_5fresource_5frequest_2eproto_2epb_2eh