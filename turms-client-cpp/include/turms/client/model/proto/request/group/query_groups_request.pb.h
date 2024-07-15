// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/group/query_groups_request.proto
// Protobuf C++ Version: 5.27.2

#ifndef GOOGLE_PROTOBUF_INCLUDED_request_2fgroup_2fquery_5fgroups_5frequest_2eproto_2epb_2eh
#define GOOGLE_PROTOBUF_INCLUDED_request_2fgroup_2fquery_5fgroups_5frequest_2eproto_2epb_2eh

#include <limits>
#include <string>
#include <type_traits>
#include <utility>

#include "google/protobuf/runtime_version.h"
#if PROTOBUF_VERSION != 5027002
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

#define PROTOBUF_INTERNAL_EXPORT_request_2fgroup_2fquery_5fgroups_5frequest_2eproto

namespace google {
namespace protobuf {
namespace internal {
class AnyMetadata;
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_request_2fgroup_2fquery_5fgroups_5frequest_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class QueryGroupsRequest;
struct QueryGroupsRequestDefaultTypeInternal;
extern QueryGroupsRequestDefaultTypeInternal _QueryGroupsRequest_default_instance_;
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

class QueryGroupsRequest final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.QueryGroupsRequest) */ {
   public:
    inline QueryGroupsRequest()
        : QueryGroupsRequest(nullptr) {
    }
    ~QueryGroupsRequest() override;
    template <typename = void>
    explicit PROTOBUF_CONSTEXPR QueryGroupsRequest(
        ::google::protobuf::internal::ConstantInitialized);

    inline QueryGroupsRequest(const QueryGroupsRequest& from)
        : QueryGroupsRequest(nullptr, from) {
    }
    inline QueryGroupsRequest(QueryGroupsRequest&& from) noexcept
        : QueryGroupsRequest(nullptr, std::move(from)) {
    }
    inline QueryGroupsRequest& operator=(const QueryGroupsRequest& from) {
        CopyFrom(from);
        return *this;
    }
    inline QueryGroupsRequest& operator=(QueryGroupsRequest&& from) noexcept {
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

    static const QueryGroupsRequest& default_instance() {
        return *internal_default_instance();
    }
    static inline const QueryGroupsRequest* internal_default_instance() {
        return reinterpret_cast<const QueryGroupsRequest*>(&_QueryGroupsRequest_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 0;
    friend void swap(QueryGroupsRequest& a, QueryGroupsRequest& b) {
        a.Swap(&b);
    }
    inline void Swap(QueryGroupsRequest* other) {
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
    void UnsafeArenaSwap(QueryGroupsRequest* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    QueryGroupsRequest* New(::google::protobuf::Arena* arena = nullptr) const final {
        return ::google::protobuf::MessageLite::DefaultConstruct<QueryGroupsRequest>(arena);
    }
    void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) final;
    void CopyFrom(const QueryGroupsRequest& from);
    void MergeFrom(const QueryGroupsRequest& from);
    bool IsInitialized() const {
        return true;
    }
    ABSL_ATTRIBUTE_REINITIALIZES void Clear() final;
    ::size_t ByteSizeLong() const final;
    ::uint8_t* _InternalSerialize(::uint8_t* target,
                                  ::google::protobuf::io::EpsCopyOutputStream* stream) const final;
    int GetCachedSize() const {
        return _impl_._cached_size_.Get();
    }

   private:
    void SharedCtor(::google::protobuf::Arena* arena);
    void SharedDtor();
    void InternalSwap(QueryGroupsRequest* other);

   private:
    friend class ::google::protobuf::internal::AnyMetadata;
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.QueryGroupsRequest";
    }

   protected:
    explicit QueryGroupsRequest(::google::protobuf::Arena* arena);
    QueryGroupsRequest(::google::protobuf::Arena* arena, const QueryGroupsRequest& from);
    QueryGroupsRequest(::google::protobuf::Arena* arena, QueryGroupsRequest&& from) noexcept
        : QueryGroupsRequest(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::MessageLite::ClassData* GetClassData() const final;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kGroupIdsFieldNumber = 1,
        kFieldsToHighlightFieldNumber = 12,
        kCustomAttributesFieldNumber = 15,
        kNameFieldNumber = 3,
        kLastUpdatedDateFieldNumber = 2,
        kSkipFieldNumber = 10,
        kLimitFieldNumber = 11,
    };
    // repeated int64 group_ids = 1;
    int group_ids_size() const;

   private:
    int _internal_group_ids_size() const;

   public:
    void clear_group_ids();
    ::int64_t group_ids(int index) const;
    void set_group_ids(int index, ::int64_t value);
    void add_group_ids(::int64_t value);
    const ::google::protobuf::RepeatedField<::int64_t>& group_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* mutable_group_ids();

   private:
    const ::google::protobuf::RepeatedField<::int64_t>& _internal_group_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* _internal_mutable_group_ids();

   public:
    // repeated int32 fields_to_highlight = 12;
    int fields_to_highlight_size() const;

   private:
    int _internal_fields_to_highlight_size() const;

   public:
    void clear_fields_to_highlight();
    ::int32_t fields_to_highlight(int index) const;
    void set_fields_to_highlight(int index, ::int32_t value);
    void add_fields_to_highlight(::int32_t value);
    const ::google::protobuf::RepeatedField<::int32_t>& fields_to_highlight() const;
    ::google::protobuf::RepeatedField<::int32_t>* mutable_fields_to_highlight();

   private:
    const ::google::protobuf::RepeatedField<::int32_t>& _internal_fields_to_highlight() const;
    ::google::protobuf::RepeatedField<::int32_t>* _internal_mutable_fields_to_highlight();

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
    // optional string name = 3;
    bool has_name() const;
    void clear_name();
    const std::string& name() const;
    template <typename Arg_ = const std::string&, typename... Args_>
    void set_name(Arg_&& arg, Args_... args);
    std::string* mutable_name();
    PROTOBUF_NODISCARD std::string* release_name();
    void set_allocated_name(std::string* value);

   private:
    const std::string& _internal_name() const;
    inline PROTOBUF_ALWAYS_INLINE void _internal_set_name(const std::string& value);
    std::string* _internal_mutable_name();

   public:
    // optional int64 last_updated_date = 2;
    bool has_last_updated_date() const;
    void clear_last_updated_date();
    ::int64_t last_updated_date() const;
    void set_last_updated_date(::int64_t value);

   private:
    ::int64_t _internal_last_updated_date() const;
    void _internal_set_last_updated_date(::int64_t value);

   public:
    // optional int32 skip = 10;
    bool has_skip() const;
    void clear_skip();
    ::int32_t skip() const;
    void set_skip(::int32_t value);

   private:
    ::int32_t _internal_skip() const;
    void _internal_set_skip(::int32_t value);

   public:
    // optional int32 limit = 11;
    bool has_limit() const;
    void clear_limit();
    ::int32_t limit() const;
    void set_limit(::int32_t value);

   private:
    ::int32_t _internal_limit() const;
    void _internal_set_limit(::int32_t value);

   public:
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.QueryGroupsRequest)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<3, 7, 1, 56, 2> _table_;

    static constexpr const void* _raw_default_instance_ = &_QueryGroupsRequest_default_instance_;

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
                              const QueryGroupsRequest& from_msg);
        ::google::protobuf::internal::HasBits<1> _has_bits_;
        mutable ::google::protobuf::internal::CachedSize _cached_size_;
        ::google::protobuf::RepeatedField<::int64_t> group_ids_;
        mutable ::google::protobuf::internal::CachedSize _group_ids_cached_byte_size_;
        ::google::protobuf::RepeatedField<::int32_t> fields_to_highlight_;
        mutable ::google::protobuf::internal::CachedSize _fields_to_highlight_cached_byte_size_;
        ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>
            custom_attributes_;
        ::google::protobuf::internal::ArenaStringPtr name_;
        ::int64_t last_updated_date_;
        ::int32_t skip_;
        ::int32_t limit_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_request_2fgroup_2fquery_5fgroups_5frequest_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// QueryGroupsRequest

// repeated int64 group_ids = 1;
inline int QueryGroupsRequest::_internal_group_ids_size() const {
    return _internal_group_ids().size();
}
inline int QueryGroupsRequest::group_ids_size() const {
    return _internal_group_ids_size();
}
inline void QueryGroupsRequest::clear_group_ids() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.group_ids_.Clear();
}
inline ::int64_t QueryGroupsRequest::group_ids(int index) const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.group_ids)
    return _internal_group_ids().Get(index);
}
inline void QueryGroupsRequest::set_group_ids(int index, ::int64_t value) {
    _internal_mutable_group_ids()->Set(index, value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.group_ids)
}
inline void QueryGroupsRequest::add_group_ids(::int64_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _internal_mutable_group_ids()->Add(value);
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryGroupsRequest.group_ids)
}
inline const ::google::protobuf::RepeatedField<::int64_t>& QueryGroupsRequest::group_ids() const
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryGroupsRequest.group_ids)
    return _internal_group_ids();
}
inline ::google::protobuf::RepeatedField<::int64_t>* QueryGroupsRequest::mutable_group_ids()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryGroupsRequest.group_ids)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_group_ids();
}
inline const ::google::protobuf::RepeatedField<::int64_t>& QueryGroupsRequest::_internal_group_ids()
    const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.group_ids_;
}
inline ::google::protobuf::RepeatedField<::int64_t>*
QueryGroupsRequest::_internal_mutable_group_ids() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.group_ids_;
}

// optional int64 last_updated_date = 2;
inline bool QueryGroupsRequest::has_last_updated_date() const {
    bool value = (_impl_._has_bits_[0] & 0x00000002u) != 0;
    return value;
}
inline void QueryGroupsRequest::clear_last_updated_date() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.last_updated_date_ = ::int64_t{0};
    _impl_._has_bits_[0] &= ~0x00000002u;
}
inline ::int64_t QueryGroupsRequest::last_updated_date() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.last_updated_date)
    return _internal_last_updated_date();
}
inline void QueryGroupsRequest::set_last_updated_date(::int64_t value) {
    _internal_set_last_updated_date(value);
    _impl_._has_bits_[0] |= 0x00000002u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.last_updated_date)
}
inline ::int64_t QueryGroupsRequest::_internal_last_updated_date() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.last_updated_date_;
}
inline void QueryGroupsRequest::_internal_set_last_updated_date(::int64_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.last_updated_date_ = value;
}

// optional string name = 3;
inline bool QueryGroupsRequest::has_name() const {
    bool value = (_impl_._has_bits_[0] & 0x00000001u) != 0;
    return value;
}
inline void QueryGroupsRequest::clear_name() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.name_.ClearToEmpty();
    _impl_._has_bits_[0] &= ~0x00000001u;
}
inline const std::string& QueryGroupsRequest::name() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.name)
    return _internal_name();
}
template <typename Arg_, typename... Args_>
inline PROTOBUF_ALWAYS_INLINE void QueryGroupsRequest::set_name(Arg_&& arg, Args_... args) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_._has_bits_[0] |= 0x00000001u;
    _impl_.name_.Set(static_cast<Arg_&&>(arg), args..., GetArena());
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.name)
}
inline std::string* QueryGroupsRequest::mutable_name() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    std::string* _s = _internal_mutable_name();
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.QueryGroupsRequest.name)
    return _s;
}
inline const std::string& QueryGroupsRequest::_internal_name() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.name_.Get();
}
inline void QueryGroupsRequest::_internal_set_name(const std::string& value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_._has_bits_[0] |= 0x00000001u;
    _impl_.name_.Set(value, GetArena());
}
inline std::string* QueryGroupsRequest::_internal_mutable_name() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_._has_bits_[0] |= 0x00000001u;
    return _impl_.name_.Mutable(GetArena());
}
inline std::string* QueryGroupsRequest::release_name() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    // @@protoc_insertion_point(field_release:turms.client.model.proto.QueryGroupsRequest.name)
    if ((_impl_._has_bits_[0] & 0x00000001u) == 0) {
        return nullptr;
    }
    _impl_._has_bits_[0] &= ~0x00000001u;
    auto* released = _impl_.name_.Release();
#ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    _impl_.name_.Set("", GetArena());
#endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
    return released;
}
inline void QueryGroupsRequest::set_allocated_name(std::string* value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    if (value != nullptr) {
        _impl_._has_bits_[0] |= 0x00000001u;
    } else {
        _impl_._has_bits_[0] &= ~0x00000001u;
    }
    _impl_.name_.SetAllocated(value, GetArena());
#ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
    if (_impl_.name_.IsDefault()) {
        _impl_.name_.Set("", GetArena());
    }
#endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
    // @@protoc_insertion_point(field_set_allocated:turms.client.model.proto.QueryGroupsRequest.name)
}

// optional int32 skip = 10;
inline bool QueryGroupsRequest::has_skip() const {
    bool value = (_impl_._has_bits_[0] & 0x00000004u) != 0;
    return value;
}
inline void QueryGroupsRequest::clear_skip() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.skip_ = 0;
    _impl_._has_bits_[0] &= ~0x00000004u;
}
inline ::int32_t QueryGroupsRequest::skip() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.skip)
    return _internal_skip();
}
inline void QueryGroupsRequest::set_skip(::int32_t value) {
    _internal_set_skip(value);
    _impl_._has_bits_[0] |= 0x00000004u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.skip)
}
inline ::int32_t QueryGroupsRequest::_internal_skip() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.skip_;
}
inline void QueryGroupsRequest::_internal_set_skip(::int32_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.skip_ = value;
}

// optional int32 limit = 11;
inline bool QueryGroupsRequest::has_limit() const {
    bool value = (_impl_._has_bits_[0] & 0x00000008u) != 0;
    return value;
}
inline void QueryGroupsRequest::clear_limit() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.limit_ = 0;
    _impl_._has_bits_[0] &= ~0x00000008u;
}
inline ::int32_t QueryGroupsRequest::limit() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.limit)
    return _internal_limit();
}
inline void QueryGroupsRequest::set_limit(::int32_t value) {
    _internal_set_limit(value);
    _impl_._has_bits_[0] |= 0x00000008u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.limit)
}
inline ::int32_t QueryGroupsRequest::_internal_limit() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.limit_;
}
inline void QueryGroupsRequest::_internal_set_limit(::int32_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.limit_ = value;
}

// repeated int32 fields_to_highlight = 12;
inline int QueryGroupsRequest::_internal_fields_to_highlight_size() const {
    return _internal_fields_to_highlight().size();
}
inline int QueryGroupsRequest::fields_to_highlight_size() const {
    return _internal_fields_to_highlight_size();
}
inline void QueryGroupsRequest::clear_fields_to_highlight() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.fields_to_highlight_.Clear();
}
inline ::int32_t QueryGroupsRequest::fields_to_highlight(int index) const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.fields_to_highlight)
    return _internal_fields_to_highlight().Get(index);
}
inline void QueryGroupsRequest::set_fields_to_highlight(int index, ::int32_t value) {
    _internal_mutable_fields_to_highlight()->Set(index, value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryGroupsRequest.fields_to_highlight)
}
inline void QueryGroupsRequest::add_fields_to_highlight(::int32_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _internal_mutable_fields_to_highlight()->Add(value);
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryGroupsRequest.fields_to_highlight)
}
inline const ::google::protobuf::RepeatedField<::int32_t>& QueryGroupsRequest::fields_to_highlight()
    const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryGroupsRequest.fields_to_highlight)
    return _internal_fields_to_highlight();
}
inline ::google::protobuf::RepeatedField<::int32_t>*
QueryGroupsRequest::mutable_fields_to_highlight() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryGroupsRequest.fields_to_highlight)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_fields_to_highlight();
}
inline const ::google::protobuf::RepeatedField<::int32_t>&
QueryGroupsRequest::_internal_fields_to_highlight() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.fields_to_highlight_;
}
inline ::google::protobuf::RepeatedField<::int32_t>*
QueryGroupsRequest::_internal_mutable_fields_to_highlight() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.fields_to_highlight_;
}

// repeated .turms.client.model.proto.Value custom_attributes = 15;
inline int QueryGroupsRequest::_internal_custom_attributes_size() const {
    return _internal_custom_attributes().size();
}
inline int QueryGroupsRequest::custom_attributes_size() const {
    return _internal_custom_attributes_size();
}
inline ::turms::client::model::proto::Value* QueryGroupsRequest::mutable_custom_attributes(
    int index) ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.QueryGroupsRequest.custom_attributes)
    return _internal_mutable_custom_attributes()->Mutable(index);
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
QueryGroupsRequest::mutable_custom_attributes() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryGroupsRequest.custom_attributes)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_custom_attributes();
}
inline const ::turms::client::model::proto::Value& QueryGroupsRequest::custom_attributes(
    int index) const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryGroupsRequest.custom_attributes)
    return _internal_custom_attributes().Get(index);
}
inline ::turms::client::model::proto::Value* QueryGroupsRequest::add_custom_attributes()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::turms::client::model::proto::Value* _add = _internal_mutable_custom_attributes()->Add();
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryGroupsRequest.custom_attributes)
    return _add;
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
QueryGroupsRequest::custom_attributes() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryGroupsRequest.custom_attributes)
    return _internal_custom_attributes();
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
QueryGroupsRequest::_internal_custom_attributes() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.custom_attributes_;
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
QueryGroupsRequest::_internal_mutable_custom_attributes() {
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

#endif  // GOOGLE_PROTOBUF_INCLUDED_request_2fgroup_2fquery_5fgroups_5frequest_2eproto_2epb_2eh
