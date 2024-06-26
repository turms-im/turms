// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/message/query_messages_request.proto
// Protobuf C++ Version: 5.26.1

#ifndef GOOGLE_PROTOBUF_INCLUDED_request_2fmessage_2fquery_5fmessages_5frequest_2eproto_2epb_2eh
#define GOOGLE_PROTOBUF_INCLUDED_request_2fmessage_2fquery_5fmessages_5frequest_2eproto_2epb_2eh

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
#include "google/protobuf/message_lite.h"
#include "google/protobuf/metadata_lite.h"
#include "google/protobuf/port_undef.inc"
#include "google/protobuf/repeated_field.h"  // IWYU pragma: export
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"

#define PROTOBUF_INTERNAL_EXPORT_request_2fmessage_2fquery_5fmessages_5frequest_2eproto

namespace google {
namespace protobuf {
namespace internal {
class AnyMetadata;
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_request_2fmessage_2fquery_5fmessages_5frequest_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class QueryMessagesRequest;
struct QueryMessagesRequestDefaultTypeInternal;
extern QueryMessagesRequestDefaultTypeInternal _QueryMessagesRequest_default_instance_;
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

class QueryMessagesRequest final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.QueryMessagesRequest) */ {
   public:
    inline QueryMessagesRequest()
        : QueryMessagesRequest(nullptr) {
    }
    ~QueryMessagesRequest() override;
    template <typename = void>
    explicit PROTOBUF_CONSTEXPR QueryMessagesRequest(
        ::google::protobuf::internal::ConstantInitialized);

    inline QueryMessagesRequest(const QueryMessagesRequest& from)
        : QueryMessagesRequest(nullptr, from) {
    }
    inline QueryMessagesRequest(QueryMessagesRequest&& from) noexcept
        : QueryMessagesRequest(nullptr, std::move(from)) {
    }
    inline QueryMessagesRequest& operator=(const QueryMessagesRequest& from) {
        CopyFrom(from);
        return *this;
    }
    inline QueryMessagesRequest& operator=(QueryMessagesRequest&& from) noexcept {
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

    static const QueryMessagesRequest& default_instance() {
        return *internal_default_instance();
    }
    static inline const QueryMessagesRequest* internal_default_instance() {
        return reinterpret_cast<const QueryMessagesRequest*>(
            &_QueryMessagesRequest_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 0;
    friend void swap(QueryMessagesRequest& a, QueryMessagesRequest& b) {
        a.Swap(&b);
    }
    inline void Swap(QueryMessagesRequest* other) {
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
    void UnsafeArenaSwap(QueryMessagesRequest* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    QueryMessagesRequest* New(::google::protobuf::Arena* arena = nullptr) const final {
        return ::google::protobuf::MessageLite::DefaultConstruct<QueryMessagesRequest>(arena);
    }
    void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) final;
    void CopyFrom(const QueryMessagesRequest& from);
    void MergeFrom(const QueryMessagesRequest& from);
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
    void InternalSwap(QueryMessagesRequest* other);

   private:
    friend class ::google::protobuf::internal::AnyMetadata;
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.QueryMessagesRequest";
    }

   protected:
    explicit QueryMessagesRequest(::google::protobuf::Arena* arena);
    QueryMessagesRequest(::google::protobuf::Arena* arena, const QueryMessagesRequest& from);
    QueryMessagesRequest(::google::protobuf::Arena* arena, QueryMessagesRequest&& from) noexcept
        : QueryMessagesRequest(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::MessageLite::ClassData* GetClassData() const final;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kIdsFieldNumber = 1,
        kFromIdsFieldNumber = 4,
        kDeliveryDateStartFieldNumber = 5,
        kAreGroupMessagesFieldNumber = 2,
        kAreSystemMessagesFieldNumber = 3,
        kWithTotalFieldNumber = 8,
        kDescendingFieldNumber = 9,
        kMaxCountFieldNumber = 7,
        kDeliveryDateEndFieldNumber = 6,
    };
    // repeated int64 ids = 1;
    int ids_size() const;

   private:
    int _internal_ids_size() const;

   public:
    void clear_ids();
    ::int64_t ids(int index) const;
    void set_ids(int index, ::int64_t value);
    void add_ids(::int64_t value);
    const ::google::protobuf::RepeatedField<::int64_t>& ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* mutable_ids();

   private:
    const ::google::protobuf::RepeatedField<::int64_t>& _internal_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* _internal_mutable_ids();

   public:
    // repeated int64 from_ids = 4;
    int from_ids_size() const;

   private:
    int _internal_from_ids_size() const;

   public:
    void clear_from_ids();
    ::int64_t from_ids(int index) const;
    void set_from_ids(int index, ::int64_t value);
    void add_from_ids(::int64_t value);
    const ::google::protobuf::RepeatedField<::int64_t>& from_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* mutable_from_ids();

   private:
    const ::google::protobuf::RepeatedField<::int64_t>& _internal_from_ids() const;
    ::google::protobuf::RepeatedField<::int64_t>* _internal_mutable_from_ids();

   public:
    // optional int64 delivery_date_start = 5;
    bool has_delivery_date_start() const;
    void clear_delivery_date_start();
    ::int64_t delivery_date_start() const;
    void set_delivery_date_start(::int64_t value);

   private:
    ::int64_t _internal_delivery_date_start() const;
    void _internal_set_delivery_date_start(::int64_t value);

   public:
    // optional bool are_group_messages = 2;
    bool has_are_group_messages() const;
    void clear_are_group_messages();
    bool are_group_messages() const;
    void set_are_group_messages(bool value);

   private:
    bool _internal_are_group_messages() const;
    void _internal_set_are_group_messages(bool value);

   public:
    // optional bool are_system_messages = 3;
    bool has_are_system_messages() const;
    void clear_are_system_messages();
    bool are_system_messages() const;
    void set_are_system_messages(bool value);

   private:
    bool _internal_are_system_messages() const;
    void _internal_set_are_system_messages(bool value);

   public:
    // bool with_total = 8;
    void clear_with_total();
    bool with_total() const;
    void set_with_total(bool value);

   private:
    bool _internal_with_total() const;
    void _internal_set_with_total(bool value);

   public:
    // optional bool descending = 9;
    bool has_descending() const;
    void clear_descending();
    bool descending() const;
    void set_descending(bool value);

   private:
    bool _internal_descending() const;
    void _internal_set_descending(bool value);

   public:
    // optional int32 max_count = 7;
    bool has_max_count() const;
    void clear_max_count();
    ::int32_t max_count() const;
    void set_max_count(::int32_t value);

   private:
    ::int32_t _internal_max_count() const;
    void _internal_set_max_count(::int32_t value);

   public:
    // optional int64 delivery_date_end = 6;
    bool has_delivery_date_end() const;
    void clear_delivery_date_end();
    ::int64_t delivery_date_end() const;
    void set_delivery_date_end(::int64_t value);

   private:
    ::int64_t _internal_delivery_date_end() const;
    void _internal_set_delivery_date_end(::int64_t value);

   public:
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.QueryMessagesRequest)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<4, 9, 0, 0, 2> _table_;
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
        ::google::protobuf::RepeatedField<::int64_t> ids_;
        mutable ::google::protobuf::internal::CachedSize _ids_cached_byte_size_;
        ::google::protobuf::RepeatedField<::int64_t> from_ids_;
        mutable ::google::protobuf::internal::CachedSize _from_ids_cached_byte_size_;
        ::int64_t delivery_date_start_;
        bool are_group_messages_;
        bool are_system_messages_;
        bool with_total_;
        bool descending_;
        ::int32_t max_count_;
        ::int64_t delivery_date_end_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_request_2fmessage_2fquery_5fmessages_5frequest_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// QueryMessagesRequest

// repeated int64 ids = 1;
inline int QueryMessagesRequest::_internal_ids_size() const {
    return _internal_ids().size();
}
inline int QueryMessagesRequest::ids_size() const {
    return _internal_ids_size();
}
inline void QueryMessagesRequest::clear_ids() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.ids_.Clear();
}
inline ::int64_t QueryMessagesRequest::ids(int index) const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.ids)
    return _internal_ids().Get(index);
}
inline void QueryMessagesRequest::set_ids(int index, ::int64_t value) {
    _internal_mutable_ids()->Set(index, value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.ids)
}
inline void QueryMessagesRequest::add_ids(::int64_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _internal_mutable_ids()->Add(value);
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryMessagesRequest.ids)
}
inline const ::google::protobuf::RepeatedField<::int64_t>& QueryMessagesRequest::ids() const
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryMessagesRequest.ids)
    return _internal_ids();
}
inline ::google::protobuf::RepeatedField<::int64_t>* QueryMessagesRequest::mutable_ids()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryMessagesRequest.ids)
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    return _internal_mutable_ids();
}
inline const ::google::protobuf::RepeatedField<::int64_t>& QueryMessagesRequest::_internal_ids()
    const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.ids_;
}
inline ::google::protobuf::RepeatedField<::int64_t>* QueryMessagesRequest::_internal_mutable_ids() {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return &_impl_.ids_;
}

// optional bool are_group_messages = 2;
inline bool QueryMessagesRequest::has_are_group_messages() const {
    bool value = (_impl_._has_bits_[0] & 0x00000002u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_are_group_messages() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.are_group_messages_ = false;
    _impl_._has_bits_[0] &= ~0x00000002u;
}
inline bool QueryMessagesRequest::are_group_messages() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.are_group_messages)
    return _internal_are_group_messages();
}
inline void QueryMessagesRequest::set_are_group_messages(bool value) {
    _internal_set_are_group_messages(value);
    _impl_._has_bits_[0] |= 0x00000002u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.are_group_messages)
}
inline bool QueryMessagesRequest::_internal_are_group_messages() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.are_group_messages_;
}
inline void QueryMessagesRequest::_internal_set_are_group_messages(bool value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.are_group_messages_ = value;
}

// optional bool are_system_messages = 3;
inline bool QueryMessagesRequest::has_are_system_messages() const {
    bool value = (_impl_._has_bits_[0] & 0x00000004u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_are_system_messages() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.are_system_messages_ = false;
    _impl_._has_bits_[0] &= ~0x00000004u;
}
inline bool QueryMessagesRequest::are_system_messages() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.are_system_messages)
    return _internal_are_system_messages();
}
inline void QueryMessagesRequest::set_are_system_messages(bool value) {
    _internal_set_are_system_messages(value);
    _impl_._has_bits_[0] |= 0x00000004u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.are_system_messages)
}
inline bool QueryMessagesRequest::_internal_are_system_messages() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.are_system_messages_;
}
inline void QueryMessagesRequest::_internal_set_are_system_messages(bool value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.are_system_messages_ = value;
}

// repeated int64 from_ids = 4;
inline int QueryMessagesRequest::_internal_from_ids_size() const {
    return _internal_from_ids().size();
}
inline int QueryMessagesRequest::from_ids_size() const {
    return _internal_from_ids_size();
}
inline void QueryMessagesRequest::clear_from_ids() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.from_ids_.Clear();
}
inline ::int64_t QueryMessagesRequest::from_ids(int index) const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.from_ids)
    return _internal_from_ids().Get(index);
}
inline void QueryMessagesRequest::set_from_ids(int index, ::int64_t value) {
    _internal_mutable_from_ids()->Set(index, value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.from_ids)
}
inline void QueryMessagesRequest::add_from_ids(::int64_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _internal_mutable_from_ids()->Add(value);
    // @@protoc_insertion_point(field_add:turms.client.model.proto.QueryMessagesRequest.from_ids)
}
inline const ::google::protobuf::RepeatedField<::int64_t>& QueryMessagesRequest::from_ids() const
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.QueryMessagesRequest.from_ids)
    return _internal_from_ids();
}
inline ::google::protobuf::RepeatedField<::int64_t>* QueryMessagesRequest::mutable_from_ids()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.QueryMessagesRequest.from_ids)
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    return _internal_mutable_from_ids();
}
inline const ::google::protobuf::RepeatedField<::int64_t>&
QueryMessagesRequest::_internal_from_ids() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.from_ids_;
}
inline ::google::protobuf::RepeatedField<::int64_t>*
QueryMessagesRequest::_internal_mutable_from_ids() {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return &_impl_.from_ids_;
}

// optional int64 delivery_date_start = 5;
inline bool QueryMessagesRequest::has_delivery_date_start() const {
    bool value = (_impl_._has_bits_[0] & 0x00000001u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_delivery_date_start() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.delivery_date_start_ = ::int64_t{0};
    _impl_._has_bits_[0] &= ~0x00000001u;
}
inline ::int64_t QueryMessagesRequest::delivery_date_start() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.delivery_date_start)
    return _internal_delivery_date_start();
}
inline void QueryMessagesRequest::set_delivery_date_start(::int64_t value) {
    _internal_set_delivery_date_start(value);
    _impl_._has_bits_[0] |= 0x00000001u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.delivery_date_start)
}
inline ::int64_t QueryMessagesRequest::_internal_delivery_date_start() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.delivery_date_start_;
}
inline void QueryMessagesRequest::_internal_set_delivery_date_start(::int64_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.delivery_date_start_ = value;
}

// optional int64 delivery_date_end = 6;
inline bool QueryMessagesRequest::has_delivery_date_end() const {
    bool value = (_impl_._has_bits_[0] & 0x00000020u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_delivery_date_end() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.delivery_date_end_ = ::int64_t{0};
    _impl_._has_bits_[0] &= ~0x00000020u;
}
inline ::int64_t QueryMessagesRequest::delivery_date_end() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.delivery_date_end)
    return _internal_delivery_date_end();
}
inline void QueryMessagesRequest::set_delivery_date_end(::int64_t value) {
    _internal_set_delivery_date_end(value);
    _impl_._has_bits_[0] |= 0x00000020u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.delivery_date_end)
}
inline ::int64_t QueryMessagesRequest::_internal_delivery_date_end() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.delivery_date_end_;
}
inline void QueryMessagesRequest::_internal_set_delivery_date_end(::int64_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.delivery_date_end_ = value;
}

// optional int32 max_count = 7;
inline bool QueryMessagesRequest::has_max_count() const {
    bool value = (_impl_._has_bits_[0] & 0x00000010u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_max_count() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.max_count_ = 0;
    _impl_._has_bits_[0] &= ~0x00000010u;
}
inline ::int32_t QueryMessagesRequest::max_count() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.max_count)
    return _internal_max_count();
}
inline void QueryMessagesRequest::set_max_count(::int32_t value) {
    _internal_set_max_count(value);
    _impl_._has_bits_[0] |= 0x00000010u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.max_count)
}
inline ::int32_t QueryMessagesRequest::_internal_max_count() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.max_count_;
}
inline void QueryMessagesRequest::_internal_set_max_count(::int32_t value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.max_count_ = value;
}

// bool with_total = 8;
inline void QueryMessagesRequest::clear_with_total() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.with_total_ = false;
}
inline bool QueryMessagesRequest::with_total() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.with_total)
    return _internal_with_total();
}
inline void QueryMessagesRequest::set_with_total(bool value) {
    _internal_set_with_total(value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.with_total)
}
inline bool QueryMessagesRequest::_internal_with_total() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.with_total_;
}
inline void QueryMessagesRequest::_internal_set_with_total(bool value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.with_total_ = value;
}

// optional bool descending = 9;
inline bool QueryMessagesRequest::has_descending() const {
    bool value = (_impl_._has_bits_[0] & 0x00000008u) != 0;
    return value;
}
inline void QueryMessagesRequest::clear_descending() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.descending_ = false;
    _impl_._has_bits_[0] &= ~0x00000008u;
}
inline bool QueryMessagesRequest::descending() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.QueryMessagesRequest.descending)
    return _internal_descending();
}
inline void QueryMessagesRequest::set_descending(bool value) {
    _internal_set_descending(value);
    _impl_._has_bits_[0] |= 0x00000008u;
    // @@protoc_insertion_point(field_set:turms.client.model.proto.QueryMessagesRequest.descending)
}
inline bool QueryMessagesRequest::_internal_descending() const {
    PROTOBUF_TSAN_READ(&_impl_._tsan_detect_race);
    return _impl_.descending_;
}
inline void QueryMessagesRequest::_internal_set_descending(bool value) {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    _impl_.descending_ = value;
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

#endif  // GOOGLE_PROTOBUF_INCLUDED_request_2fmessage_2fquery_5fmessages_5frequest_2eproto_2epb_2eh