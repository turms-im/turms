// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/conversation/update_typing_status_request.proto
// Protobuf C++ Version: 5.29.1

#ifndef request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto_2epb_2eh
#define request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto_2epb_2eh

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

#define PROTOBUF_INTERNAL_EXPORT_request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto

namespace google {
namespace protobuf {
namespace internal {
template <typename T>
::absl::string_view GetAnyMessageName();
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class UpdateTypingStatusRequest;
struct UpdateTypingStatusRequestDefaultTypeInternal;
extern UpdateTypingStatusRequestDefaultTypeInternal _UpdateTypingStatusRequest_default_instance_;
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

class UpdateTypingStatusRequest final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.UpdateTypingStatusRequest) */
{
   public:
    inline UpdateTypingStatusRequest()
        : UpdateTypingStatusRequest(nullptr) {
    }
    ~UpdateTypingStatusRequest() PROTOBUF_FINAL;

#if defined(PROTOBUF_CUSTOM_VTABLE)
    void operator delete(UpdateTypingStatusRequest* msg, std::destroying_delete_t) {
        SharedDtor(*msg);
        ::google::protobuf::internal::SizedDelete(msg, sizeof(UpdateTypingStatusRequest));
    }
#endif

    template <typename = void>
    explicit PROTOBUF_CONSTEXPR UpdateTypingStatusRequest(
        ::google::protobuf::internal::ConstantInitialized);

    inline UpdateTypingStatusRequest(const UpdateTypingStatusRequest& from)
        : UpdateTypingStatusRequest(nullptr, from) {
    }
    inline UpdateTypingStatusRequest(UpdateTypingStatusRequest&& from) noexcept
        : UpdateTypingStatusRequest(nullptr, std::move(from)) {
    }
    inline UpdateTypingStatusRequest& operator=(const UpdateTypingStatusRequest& from) {
        CopyFrom(from);
        return *this;
    }
    inline UpdateTypingStatusRequest& operator=(UpdateTypingStatusRequest&& from) noexcept {
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

    static const UpdateTypingStatusRequest& default_instance() {
        return *internal_default_instance();
    }
    static inline const UpdateTypingStatusRequest* internal_default_instance() {
        return reinterpret_cast<const UpdateTypingStatusRequest*>(
            &_UpdateTypingStatusRequest_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 0;
    friend void swap(UpdateTypingStatusRequest& a, UpdateTypingStatusRequest& b) {
        a.Swap(&b);
    }
    inline void Swap(UpdateTypingStatusRequest* other) {
        if (other == this)
            return;
        if (::google::protobuf::internal::CanUseInternalSwap(GetArena(), other->GetArena())) {
            InternalSwap(other);
        } else {
            ::google::protobuf::internal::GenericSwap(this, other);
        }
    }
    void UnsafeArenaSwap(UpdateTypingStatusRequest* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    UpdateTypingStatusRequest* New(::google::protobuf::Arena* arena = nullptr) const {
        return ::google::protobuf::MessageLite::DefaultConstruct<UpdateTypingStatusRequest>(arena);
    }
    void CopyFrom(const UpdateTypingStatusRequest& from);
    void MergeFrom(const UpdateTypingStatusRequest& from) {
        UpdateTypingStatusRequest::MergeImpl(*this, from);
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
    void InternalSwap(UpdateTypingStatusRequest* other);

   private:
    template <typename T>
    friend ::absl::string_view(::google::protobuf::internal::GetAnyMessageName)();
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.UpdateTypingStatusRequest";
    }

   protected:
    explicit UpdateTypingStatusRequest(::google::protobuf::Arena* arena);
    UpdateTypingStatusRequest(::google::protobuf::Arena* arena,
                              const UpdateTypingStatusRequest& from);
    UpdateTypingStatusRequest(::google::protobuf::Arena* arena,
                              UpdateTypingStatusRequest&& from) noexcept
        : UpdateTypingStatusRequest(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::internal::ClassData* GetClassData() const PROTOBUF_FINAL;
    static void* PlacementNew_(const void*, void* mem, ::google::protobuf::Arena* arena);
    static constexpr auto InternalNewImpl_();
    static const ::google::protobuf::internal::ClassDataLite<51> _class_data_;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kCustomAttributesFieldNumber = 15,
        kToIdFieldNumber = 2,
        kIsGroupMessageFieldNumber = 1,
    };
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
    // int64 to_id = 2;
    void clear_to_id();
    ::int64_t to_id() const;
    void set_to_id(::int64_t value);

   private:
    ::int64_t _internal_to_id() const;
    void _internal_set_to_id(::int64_t value);

   public:
    // bool is_group_message = 1;
    void clear_is_group_message();
    bool is_group_message() const;
    void set_is_group_message(bool value);

   private:
    bool _internal_is_group_message() const;
    void _internal_set_is_group_message(bool value);

   public:
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.UpdateTypingStatusRequest)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<2, 3, 1, 0, 2> _table_;

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
                              const UpdateTypingStatusRequest& from_msg);
        ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>
            custom_attributes_;
        ::int64_t to_id_;
        bool is_group_message_;
        ::google::protobuf::internal::CachedSize _cached_size_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// UpdateTypingStatusRequest

// bool is_group_message = 1;
inline void UpdateTypingStatusRequest::clear_is_group_message() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.is_group_message_ = false;
}
inline bool UpdateTypingStatusRequest::is_group_message() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.UpdateTypingStatusRequest.is_group_message)
    return _internal_is_group_message();
}
inline void UpdateTypingStatusRequest::set_is_group_message(bool value) {
    _internal_set_is_group_message(value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.UpdateTypingStatusRequest.is_group_message)
}
inline bool UpdateTypingStatusRequest::_internal_is_group_message() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.is_group_message_;
}
inline void UpdateTypingStatusRequest::_internal_set_is_group_message(bool value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.is_group_message_ = value;
}

// int64 to_id = 2;
inline void UpdateTypingStatusRequest::clear_to_id() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.to_id_ = ::int64_t{0};
}
inline ::int64_t UpdateTypingStatusRequest::to_id() const {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.UpdateTypingStatusRequest.to_id)
    return _internal_to_id();
}
inline void UpdateTypingStatusRequest::set_to_id(::int64_t value) {
    _internal_set_to_id(value);
    // @@protoc_insertion_point(field_set:turms.client.model.proto.UpdateTypingStatusRequest.to_id)
}
inline ::int64_t UpdateTypingStatusRequest::_internal_to_id() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.to_id_;
}
inline void UpdateTypingStatusRequest::_internal_set_to_id(::int64_t value) {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.to_id_ = value;
}

// repeated .turms.client.model.proto.Value custom_attributes = 15;
inline int UpdateTypingStatusRequest::_internal_custom_attributes_size() const {
    return _internal_custom_attributes().size();
}
inline int UpdateTypingStatusRequest::custom_attributes_size() const {
    return _internal_custom_attributes_size();
}
inline ::turms::client::model::proto::Value* UpdateTypingStatusRequest::mutable_custom_attributes(
    int index) ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.UpdateTypingStatusRequest.custom_attributes)
    return _internal_mutable_custom_attributes()->Mutable(index);
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
UpdateTypingStatusRequest::mutable_custom_attributes() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.UpdateTypingStatusRequest.custom_attributes)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_custom_attributes();
}
inline const ::turms::client::model::proto::Value& UpdateTypingStatusRequest::custom_attributes(
    int index) const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.UpdateTypingStatusRequest.custom_attributes)
    return _internal_custom_attributes().Get(index);
}
inline ::turms::client::model::proto::Value* UpdateTypingStatusRequest::add_custom_attributes()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::turms::client::model::proto::Value* _add = _internal_mutable_custom_attributes()->Add();
    // @@protoc_insertion_point(field_add:turms.client.model.proto.UpdateTypingStatusRequest.custom_attributes)
    return _add;
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
UpdateTypingStatusRequest::custom_attributes() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.UpdateTypingStatusRequest.custom_attributes)
    return _internal_custom_attributes();
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>&
UpdateTypingStatusRequest::_internal_custom_attributes() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.custom_attributes_;
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::Value>*
UpdateTypingStatusRequest::_internal_mutable_custom_attributes() {
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

#endif  // request_2fconversation_2fupdate_5ftyping_5fstatus_5frequest_2eproto_2epb_2eh