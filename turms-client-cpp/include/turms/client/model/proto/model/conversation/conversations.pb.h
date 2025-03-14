// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: model/conversation/conversations.proto
// Protobuf C++ Version: 5.29.1

#ifndef model_2fconversation_2fconversations_2eproto_2epb_2eh
#define model_2fconversation_2fconversations_2eproto_2epb_2eh

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
#include "turms/client/model/proto/model/conversation/group_conversation.pb.h"
#include "turms/client/model/proto/model/conversation/private_conversation.pb.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"

#define PROTOBUF_INTERNAL_EXPORT_model_2fconversation_2fconversations_2eproto

namespace google {
namespace protobuf {
namespace internal {
template <typename T>
::absl::string_view GetAnyMessageName();
}  // namespace internal
}  // namespace protobuf
}  // namespace google

// Internal implementation detail -- do not use these members.
struct TableStruct_model_2fconversation_2fconversations_2eproto {
    static const ::uint32_t offsets[];
};
namespace turms {
namespace client {
namespace model {
namespace proto {
class Conversations;
struct ConversationsDefaultTypeInternal;
extern ConversationsDefaultTypeInternal _Conversations_default_instance_;
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

class Conversations final : public ::google::protobuf::MessageLite
/* @@protoc_insertion_point(class_definition:turms.client.model.proto.Conversations) */ {
   public:
    inline Conversations()
        : Conversations(nullptr) {
    }
    ~Conversations() PROTOBUF_FINAL;

#if defined(PROTOBUF_CUSTOM_VTABLE)
    void operator delete(Conversations* msg, std::destroying_delete_t) {
        SharedDtor(*msg);
        ::google::protobuf::internal::SizedDelete(msg, sizeof(Conversations));
    }
#endif

    template <typename = void>
    explicit PROTOBUF_CONSTEXPR Conversations(::google::protobuf::internal::ConstantInitialized);

    inline Conversations(const Conversations& from)
        : Conversations(nullptr, from) {
    }
    inline Conversations(Conversations&& from) noexcept
        : Conversations(nullptr, std::move(from)) {
    }
    inline Conversations& operator=(const Conversations& from) {
        CopyFrom(from);
        return *this;
    }
    inline Conversations& operator=(Conversations&& from) noexcept {
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

    static const Conversations& default_instance() {
        return *internal_default_instance();
    }
    static inline const Conversations* internal_default_instance() {
        return reinterpret_cast<const Conversations*>(&_Conversations_default_instance_);
    }
    static constexpr int kIndexInFileMessages = 0;
    friend void swap(Conversations& a, Conversations& b) {
        a.Swap(&b);
    }
    inline void Swap(Conversations* other) {
        if (other == this)
            return;
        if (::google::protobuf::internal::CanUseInternalSwap(GetArena(), other->GetArena())) {
            InternalSwap(other);
        } else {
            ::google::protobuf::internal::GenericSwap(this, other);
        }
    }
    void UnsafeArenaSwap(Conversations* other) {
        if (other == this)
            return;
        ABSL_DCHECK(GetArena() == other->GetArena());
        InternalSwap(other);
    }

    // implements Message ----------------------------------------------

    Conversations* New(::google::protobuf::Arena* arena = nullptr) const {
        return ::google::protobuf::MessageLite::DefaultConstruct<Conversations>(arena);
    }
    void CopyFrom(const Conversations& from);
    void MergeFrom(const Conversations& from) {
        Conversations::MergeImpl(*this, from);
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
    void InternalSwap(Conversations* other);

   private:
    template <typename T>
    friend ::absl::string_view(::google::protobuf::internal::GetAnyMessageName)();
    static ::absl::string_view FullMessageName() {
        return "turms.client.model.proto.Conversations";
    }

   protected:
    explicit Conversations(::google::protobuf::Arena* arena);
    Conversations(::google::protobuf::Arena* arena, const Conversations& from);
    Conversations(::google::protobuf::Arena* arena, Conversations&& from) noexcept
        : Conversations(arena) {
        *this = ::std::move(from);
    }
    const ::google::protobuf::internal::ClassData* GetClassData() const PROTOBUF_FINAL;
    static void* PlacementNew_(const void*, void* mem, ::google::protobuf::Arena* arena);
    static constexpr auto InternalNewImpl_();
    static const ::google::protobuf::internal::ClassDataLite<39> _class_data_;

   public:
    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------
    enum : int {
        kPrivateConversationsFieldNumber = 1,
        kGroupConversationsFieldNumber = 2,
    };
    // repeated .turms.client.model.proto.PrivateConversation private_conversations = 1;
    int private_conversations_size() const;

   private:
    int _internal_private_conversations_size() const;

   public:
    void clear_private_conversations();
    ::turms::client::model::proto::PrivateConversation* mutable_private_conversations(int index);
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>*
    mutable_private_conversations();

   private:
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>&
    _internal_private_conversations() const;
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>*
    _internal_mutable_private_conversations();

   public:
    const ::turms::client::model::proto::PrivateConversation& private_conversations(
        int index) const;
    ::turms::client::model::proto::PrivateConversation* add_private_conversations();
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>&
    private_conversations() const;
    // repeated .turms.client.model.proto.GroupConversation group_conversations = 2;
    int group_conversations_size() const;

   private:
    int _internal_group_conversations_size() const;

   public:
    void clear_group_conversations();
    ::turms::client::model::proto::GroupConversation* mutable_group_conversations(int index);
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>*
    mutable_group_conversations();

   private:
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>&
    _internal_group_conversations() const;
    ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>*
    _internal_mutable_group_conversations();

   public:
    const ::turms::client::model::proto::GroupConversation& group_conversations(int index) const;
    ::turms::client::model::proto::GroupConversation* add_group_conversations();
    const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>&
    group_conversations() const;
    // @@protoc_insertion_point(class_scope:turms.client.model.proto.Conversations)
   private:
    class _Internal;
    friend class ::google::protobuf::internal::TcParser;
    static const ::google::protobuf::internal::TcParseTable<1, 2, 2, 0, 2> _table_;

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
                              const Conversations& from_msg);
        ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>
            private_conversations_;
        ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>
            group_conversations_;
        ::google::protobuf::internal::CachedSize _cached_size_;
        PROTOBUF_TSAN_DECLARE_MEMBER
    };
    union {
        Impl_ _impl_;
    };
    friend struct ::TableStruct_model_2fconversation_2fconversations_2eproto;
};

// ===================================================================

// ===================================================================

#ifdef __GNUC__
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// -------------------------------------------------------------------

// Conversations

// repeated .turms.client.model.proto.PrivateConversation private_conversations = 1;
inline int Conversations::_internal_private_conversations_size() const {
    return _internal_private_conversations().size();
}
inline int Conversations::private_conversations_size() const {
    return _internal_private_conversations_size();
}
inline ::turms::client::model::proto::PrivateConversation*
Conversations::mutable_private_conversations(int index) ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.Conversations.private_conversations)
    return _internal_mutable_private_conversations()->Mutable(index);
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>*
Conversations::mutable_private_conversations() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.Conversations.private_conversations)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_private_conversations();
}
inline const ::turms::client::model::proto::PrivateConversation&
Conversations::private_conversations(int index) const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.Conversations.private_conversations)
    return _internal_private_conversations().Get(index);
}
inline ::turms::client::model::proto::PrivateConversation*
Conversations::add_private_conversations() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::turms::client::model::proto::PrivateConversation* _add =
        _internal_mutable_private_conversations()->Add();
    // @@protoc_insertion_point(field_add:turms.client.model.proto.Conversations.private_conversations)
    return _add;
}
inline const ::google::protobuf::RepeatedPtrField<
    ::turms::client::model::proto::PrivateConversation>&
Conversations::private_conversations() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.Conversations.private_conversations)
    return _internal_private_conversations();
}
inline const ::google::protobuf::RepeatedPtrField<
    ::turms::client::model::proto::PrivateConversation>&
Conversations::_internal_private_conversations() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.private_conversations_;
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::PrivateConversation>*
Conversations::_internal_mutable_private_conversations() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.private_conversations_;
}

// repeated .turms.client.model.proto.GroupConversation group_conversations = 2;
inline int Conversations::_internal_group_conversations_size() const {
    return _internal_group_conversations().size();
}
inline int Conversations::group_conversations_size() const {
    return _internal_group_conversations_size();
}
inline ::turms::client::model::proto::GroupConversation* Conversations::mutable_group_conversations(
    int index) ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable:turms.client.model.proto.Conversations.group_conversations)
    return _internal_mutable_group_conversations()->Mutable(index);
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>*
Conversations::mutable_group_conversations() ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_mutable_list:turms.client.model.proto.Conversations.group_conversations)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    return _internal_mutable_group_conversations();
}
inline const ::turms::client::model::proto::GroupConversation& Conversations::group_conversations(
    int index) const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_get:turms.client.model.proto.Conversations.group_conversations)
    return _internal_group_conversations().Get(index);
}
inline ::turms::client::model::proto::GroupConversation* Conversations::add_group_conversations()
    ABSL_ATTRIBUTE_LIFETIME_BOUND {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::turms::client::model::proto::GroupConversation* _add =
        _internal_mutable_group_conversations()->Add();
    // @@protoc_insertion_point(field_add:turms.client.model.proto.Conversations.group_conversations)
    return _add;
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>&
Conversations::group_conversations() const ABSL_ATTRIBUTE_LIFETIME_BOUND {
    // @@protoc_insertion_point(field_list:turms.client.model.proto.Conversations.group_conversations)
    return _internal_group_conversations();
}
inline const ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>&
Conversations::_internal_group_conversations() const {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return _impl_.group_conversations_;
}
inline ::google::protobuf::RepeatedPtrField<::turms::client::model::proto::GroupConversation>*
Conversations::_internal_mutable_group_conversations() {
    ::google::protobuf::internal::TSanRead(&_impl_);
    return &_impl_.group_conversations_;
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

#endif  // model_2fconversation_2fconversations_2eproto_2epb_2eh