// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/user/query_user_online_statuses_request.proto
// Protobuf C++ Version: 5.29.1

#ifndef request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto_2epb_2eh
#define request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto_2epb_2eh

#include <limits>
#include <string>
#include <type_traits>
#include <utility>

#include "google/protobuf/runtime_version.h"
#if PROTOBUF_VERSION != 5029001
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
#include "google/protobuf/message_lite.h"
#include "google/protobuf/metadata_lite.h"
#include "google/protobuf/repeated_field.h"  // IWYU pragma: export
#include "turms/client/model/proto/model/common/value.pb.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"

#define PROTOBUF_INTERNAL_EXPORT_request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto

namespace google {
namespace protobuf {
namespace internal {
template <typename T>
::absl::string_view GetAnyMessageName();
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class QueryUserOnlineStatusesRequest;
struct QueryUserOnlineStatusesRequestDefaultTypeInternal;
extern QueryUserOnlineStatusesRequestDefaultTypeInternal
    _QueryUserOnlineStatusesRequest_default_instance_;
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

class QueryUserOnlineStatusesRequest final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.QueryUserOnlineStatusesRequest)
 */
{
   public:
    inline QueryUserOnlineStatusesRequest()
        : QueryUserOnlineStatusesRequest(nullptr) {
    }
    ~QueryUserOnlineStatusesRequest() PROTOBUF_FINAL;

#if defined(PROTOBUF_CUSTOM_VTABLE)
    void operator delete(QueryUserOnlineStatusesRequest* msg, std::destroying_delete_t) {
        SharedDtor(*msg);
        ::google::protobuf::internal::SizedDelete(msg, sizeof(QueryUserOnlineStatusesRequest));
    }
#endif

    template <typename = void>
    explicit PROTOBUF_CONSTEXPR QueryUserOnlineStatusesRequest(
        ::google::protobuf::internal::ConstantInitialized);

    inline QueryUserOnlineStatusesRequest(const QueryUserOnlineStatusesRequest& from)
        : QueryUserOnlineStatusesRequest(nullptr, from) {
    }
    inline QueryUserOnlineStatusesRequest(QueryUserOnlineStatusesRequest&& from) noexcept
        : QueryUserOnlineStatusesRequest(nullptr, std::move(from)) {
    }
    inline QueryUserOnlineStatusesRequest& operator=(const QueryUserOnlineStatusesRequest& from) {
        CopyFrom(from);
        return *this;
    }
    inline QueryUserOnlineStatusesRequest& operator=(
        QueryUserOnlineStatusesRequest&& from) noexcept {
        if (this == &from)
            return *this;
        if (::google::protobuf::internal::CanMoveWithInternalSwap(GetArena(), from.GetArena())) {
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

    static const QueryUserOnlineStatusesRequest& default_instance() {
        return *internal_default_instance();
    }
    static inline const QueryUserOnlineStatusesRequest* internal_default_instance() {
        return reinterpret_cast<const QueryUserOnlineStatusesRequest*>(
            &_QueryUserOnlineStatusesRequest_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 0;
    friend void swap(QueryUserOnlineStatusesRequest& a, QueryUserOnlineStatusesRequest& b) {
        a.Swap(&b);
    }
    inline void Swap(QueryUserOnlineStatusesRequest* other) {
        if (other == this)
            return;
        if (::google::protobuf::internal::CanUseInternalSwap(GetArena(), other->GetArena())) {
            InternalSwap(other);
        } else {
            ::google::protobuf::internal::GenericSwap(this, other);
        }
    }
    void UnsafeArenaSwap(QueryUserOnlineStatusesRequest* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    QueryUserOnlineStatusesRequest* New(::google::protobuf::Arena* arena = nullptr) const {
        return ::google::protobuf::MessageLite::DefaultConstruct<QueryUserOnlineStatusesRequest>(
            arena);
    }
    void CopyFrom(const QueryUserOnlineStatusesRequest& from);
    void MergeFrom(const QueryUserOnlineStatusesRequest& from) {
        QueryUserOnlineStatusesRequest::MergeImpl(*this, from);
    }

   private:
    static void MergeImpl(::google::protobuf::MessageLite& to_msg,
                          const ::google::protobuf::MessageLite& from_msg);

   public:
    bool IsInitialized() const {
        return true;
    }
    ABSL_ATTRIBUTE_REINITIALIZES void Clear() PROTOBUF_FINAL;
#if defined(PROTOBUF_CUSTOM_VTABLE)
   private:
    static ::size_t ByteSizeLong(const ::google::protobuf::MessageLite& msg);
    static ::uint8_t* _InternalSerialize(const MessageLite& msg,
                                         ::uint8_t* target,
                                         ::google::protobuf::io::EpsCopyOutputStream* stream);

   public:
    ::size_t ByteSizeLong() const {
        return ByteSizeLong(*this);
    }
    ::uint8_t* _InternalSerialize(::uint8_t* target,
                                  ::google::protobuf::io::EpsCopyOutputStream* stream) const {
        return _InternalSerialize(*this, target, stream);
    }
#else   // PROTOBUF_CUSTOM_VTABLE
    ::size_t ByteSizeLong() const final;
    ::uint8_t* _InternalSerialize(::uint8_t* target,
                                  ::google::protobuf::io::EpsCopyOutputStream* stream) const final;
#endif  // PROTOBUF_CUSTOM_VTABLE
    int GetCachedSize() const {
        return _impl_._cached_size_.Get();
    }

   private:
    void SharedCtor(::google::protobuf::Arena* arena);
    static void SharedDtor(MessageLite& self);
    void InternalSwap(QueryUserOnlineStatusesRequest* other);

   private:
    template <typename T>
    friend ::absl::string_view(::google::protobuf::internal::GetAnyMessageName)();
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.QueryUserOnlineStatusesRequest";
    }

   protected:
    explicit QueryUserOnlineStatusesRequest(::google::protobuf::Arena* arena);
    QueryUserOnlineStatusesRequest(::google::protobuf::Arena* arena,
                                   const QueryUserOnlineStatusesRequest& from);
    QueryUserOnlineStatusesRequest(::google::protobuf::Arena* arena,
                                   QueryUserOnlineStatusesRequest&& from) noexcept
        : QueryUserOnlineStatusesRequest(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::internal::ClassData* GetClassData() const PROTOBUF_FINAL;
    static void* PlacementNew_(const void*, void* mem, ::google::protobuf::Arena* arena);
    static constexpr auto InternalNewImpl_();
    static const ::google::protobuf::internal::ClassDataLite<56> _class_data_;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kUserIdsFieldNumber = 1,
        kCustomAttributesFieldNumber = 15,
    };
    // repeated int64 user_ids = 1;
    int user_ids_size() const;

   private:
    int _internal_user_ids_size() const;

   public:
    void clear_user_ids();
    ::int64_t user_ids(int index) const;
    void set_user_ids(int index, ::int64_t value);
    void add_user_ids(::int64_t value);
    const ::google::protobuf::RepeatedField<::int64_t>& user_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* mutable_user_ids();

   private:
    const ::google::protobuf::RepeatedField<::int64_t>& _internal_user_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* _internal_mutable_user_ids();

   public:
    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    int custom_attributes_size() const;

   private:
    int _internal_custom_attributes_size() const;

   public:
    void clear_custom_attributes();
    ::turms::client::model::proto::Value* mutable_custom_attributes(int index);
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
    mutable_custom_attributes();

   private:
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
    _internal_custom_attributes() const;
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
    _internal_mutable_custom_attributes();

   public:
    const ::turms::client::model::proto::Value& custom_attributes(int index) const;
    ::turms::client::model::proto::Value* add_custom_attributes();
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
    custom_attributes() const;
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.QueryUserOnlineStatusesRequest)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<2, 2, 1, 0, 2> _table_;

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
                              const Impl_& from,
                              const QueryUserOnlineStatusesRequest& from_msg);
        ::google::protobuf::RepeatedField<::int64_t> user_ids_;
        ::google::protobuf::internal::CachedSize _user_ids_cached_byte_size_;
        ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>
            custom_attributes_;
        ::google::protobuf::internal::CachedSize _cached_size_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// QueryUserOnlineStatusesRequest

// repeated int64 user_ids = 1;
inline int QueryUserOnlineStatusesRequest::_internal_user_ids_size() const {
    return _internal_user_ids().size();
}
inline int QueryUserOnlineStatusesRequest::user_ids_size() const {
    return _internal_user_ids_size();
}
inline void QueryUserOnlineStatusesRequest::clear_user_ids() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.user_ids_.Clear();
}
inline ::int64_t QueryUserOnlineStatusesRequest::user_ids(int index) const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryUserOnlineStatusesRequest.user_ids)
    return _internal_user_ids().Get(index);
}
inline void QueryUserOnlineStatusesRequest::set_user_ids(int index, ::int64_t value) {
    _internal_mutable_user_ids()->Set(index, value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryUserOnlineStatusesRequest.user_ids)
}
inline void QueryUserOnlineStatusesRequest::add_user_ids(::int64_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _internal_mutable_user_ids()->Add(value);
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryUserOnlineStatusesRequest.user_ids)
}
inline const ::google::protobuf::RepeatedField<::int64_t>&
QueryUserOnlineStatusesRequest::user_ids() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryUserOnlineStatusesRequest.user_ids)
    return _internal_user_ids();
}
inline ::google::protobuf::RepeatedField<::int64_t>*
QueryUserOnlineStatusesRequest::mutable_user_ids() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryUserOnlineStatusesRequest.user_ids)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_user_ids();
}
inline const ::google::protobuf::RepeatedField<::int64_t>&
QueryUserOnlineStatusesRequest::_internal_user_ids() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.user_ids_;
}
inline ::google::protobuf::RepeatedField<::int64_t>*
QueryUserOnlineStatusesRequest::_internal_mutable_user_ids() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.user_ids_;
}

// repeated .turms.client.model.proto.Value custom_attributes = 15;
inline int QueryUserOnlineStatusesRequest::_internal_custom_attributes_size() const {
    return _internal_custom_attributes().size();
}
inline int QueryUserOnlineStatusesRequest::custom_attributes_size() const {
    return _internal_custom_attributes_size();
}
inline ::turms::client::model::proto::Value*
QueryUserOnlineStatusesRequest::mutable_custom_attributes(int index) ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.QueryUserOnlineStatusesRequest.custom_attributes)
    return _internal_mutable_custom_attributes()->Mutable(index);
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
QueryUserOnlineStatusesRequest::mutable_custom_attributes() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryUserOnlineStatusesRequest.custom_attributes)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_custom_attributes();
}
inline const ::turms::client::model::proto::Value&
QueryUserOnlineStatusesRequest::custom_attributes(int index) const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryUserOnlineStatusesRequest.custom_attributes)
    return _internal_custom_attributes().Get(index);
}
inline ::turms::client::model::proto::Value* QueryUserOnlineStatusesRequest::add_custom_attributes()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::turms::client::model::proto::Value* _add = _internal_mutable_custom_attributes()->Add();
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryUserOnlineStatusesRequest.custom_attributes)
    return _add;
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
QueryUserOnlineStatusesRequest::custom_attributes() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryUserOnlineStatusesRequest.custom_attributes)
    return _internal_custom_attributes();
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
QueryUserOnlineStatusesRequest::_internal_custom_attributes() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.custom_attributes_;
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
QueryUserOnlineStatusesRequest::_internal_mutable_custom_attributes() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.custom_attributes_;
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

#endif  // request_2fuser_2fquery_5fuser_5fonline_5fstatuses_5frequest_2eproto_2epb_2eh