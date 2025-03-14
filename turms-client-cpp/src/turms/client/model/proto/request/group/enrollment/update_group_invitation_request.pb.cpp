// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/group/enrollment/update_group_invitation_request.proto
// Protobuf C++ Version: 5.29.1

#include "turms/client/model/proto/request/group/enrollment/update_group_invitation_request.pb.h"

#include <algorithm>
#include <type_traits>

#include "google/protobuf/extension_set.h"
#include "google/protobuf/generated_message_tctable_impl.h"
#include "google/protobuf/generated_message_util.h"
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/io/zero_copy_stream_impl_lite.h"
#include "google/protobuf/wire_format_lite.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"
PROTOBUF_PRAGMA_INIT_SEG
namespace _pb = ::google::protobuf;
namespace _pbi = ::google::protobuf::internal;
namespace _fl = ::google::protobuf::internal::field_layout;
namespace turms {
namespace client {
namespace model {
namespace proto {

inline constexpr UpdateGroupInvitationRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      reason_(&::google::protobuf::internal::fixed_address_empty_string,
              ::_pbi::ConstantInitialized()),
      invitation_id_{::int64_t{0}},
      response_action_{static_cast<::turms::client::model::proto::ResponseAction>(0)} {
}

template <typename>
PROTOBUF_CONSTEXPR UpdateGroupInvitationRequest::UpdateGroupInvitationRequest(
    ::_pbi::ConstantInitialized)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(_class_data_.base()),
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(),
#endif  // PROTOBUF_CUSTOM_VTABLE
      _impl_(::_pbi::ConstantInitialized()) {
}
struct UpdateGroupInvitationRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UpdateGroupInvitationRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UpdateGroupInvitationRequestDefaultTypeInternal() {
    }
    union {
        UpdateGroupInvitationRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UpdateGroupInvitationRequestDefaultTypeInternal _UpdateGroupInvitationRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UpdateGroupInvitationRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<UpdateGroupInvitationRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_._has_bits_);
};

void UpdateGroupInvitationRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
UpdateGroupInvitationRequest::UpdateGroupInvitationRequest(::google::protobuf::Arena* arena)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UpdateGroupInvitationRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateGroupInvitationRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::UpdateGroupInvitationRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      reason_(arena, from.reason_) {
}

UpdateGroupInvitationRequest::UpdateGroupInvitationRequest(::google::protobuf::Arena* arena,
                                                           const UpdateGroupInvitationRequest& from)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    UpdateGroupInvitationRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, invitation_id_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, invitation_id_),
             offsetof(Impl_, response_action_) - offsetof(Impl_, invitation_id_) +
                 sizeof(Impl_::response_action_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UpdateGroupInvitationRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateGroupInvitationRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena},
      reason_(arena) {
}

inline void UpdateGroupInvitationRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, invitation_id_),
             0,
             offsetof(Impl_, response_action_) - offsetof(Impl_, invitation_id_) +
                 sizeof(Impl_::response_action_));
}
UpdateGroupInvitationRequest::~UpdateGroupInvitationRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UpdateGroupInvitationRequest)
    SharedDtor(*this);
}
inline void UpdateGroupInvitationRequest::SharedDtor(MessageLite& self) {
    UpdateGroupInvitationRequest& this_ = static_cast<UpdateGroupInvitationRequest&>(self);
    this_._internal_metadata_.Delete<std::string>();
    ABSL_DCHECK(this_.GetArena() == nullptr);
    this_._impl_.reason_.Destroy();
    this_._impl_.~Impl_();
}

inline void* UpdateGroupInvitationRequest::PlacementNew_(const void*,
                                                         void* mem,
                                                         ::google::protobuf::Arena* arena) {
    return ::new (mem) UpdateGroupInvitationRequest(arena);
}
constexpr auto UpdateGroupInvitationRequest::InternalNewImpl_() {
    constexpr auto arena_bits = ::google::protobuf::internal::EncodePlacementArenaOffsets({
        PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.custom_attributes_) +
            decltype(UpdateGroupInvitationRequest::_impl_.custom_attributes_)::
                InternalGetArenaOffset(::google::protobuf::MessageLite::internal_visibility()),
    });
    if (arena_bits.has_value()) {
        return ::google::protobuf::internal::MessageCreator::CopyInit(
            sizeof(UpdateGroupInvitationRequest),
            alignof(UpdateGroupInvitationRequest),
            *arena_bits);
    } else {
        return ::google::protobuf::internal::MessageCreator(
            &UpdateGroupInvitationRequest::PlacementNew_,
            sizeof(UpdateGroupInvitationRequest),
            alignof(UpdateGroupInvitationRequest));
    }
}
PROTOBUF_CONSTINIT
PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::google::protobuf::internal::ClassDataLite<54> UpdateGroupInvitationRequest::_class_data_ = {
    {
        &_UpdateGroupInvitationRequest_default_instance_._instance,
        &_table_.header,
        nullptr,  // OnDemandRegisterArenaDtor
        nullptr,  // IsInitialized
        &UpdateGroupInvitationRequest::MergeImpl,
        ::google::protobuf::MessageLite::GetNewImpl<UpdateGroupInvitationRequest>(),
#if defined(PROTOBUF_CUSTOM_VTABLE)
        &UpdateGroupInvitationRequest::SharedDtor,
        ::google::protobuf::MessageLite::GetClearImpl<UpdateGroupInvitationRequest>(),
        &UpdateGroupInvitationRequest::ByteSizeLong,
        &UpdateGroupInvitationRequest::_InternalSerialize,
#endif  // PROTOBUF_CUSTOM_VTABLE
        PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_._cached_size_),
        true,
    },
    "turms.client.model.proto.UpdateGroupInvitationRequest",
};
const ::google::protobuf::internal::ClassData* UpdateGroupInvitationRequest::GetClassData() const {
    return _class_data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 4, 1, 68, 2>
    UpdateGroupInvitationRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950904,  // skipmap
            offsetof(decltype(_table_), field_entries),
            4,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            _class_data_.base(),
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UpdateGroupInvitationRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // int64 invitation_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.invitation_id_)}},
            // .turms.client.model.proto.ResponseAction response_action = 2;
            {::_pbi::TcParser::FastV32S1,
             {16,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.response_action_)}},
            // optional string reason = 3;
            {::_pbi::TcParser::FastUS1,
             {26, 0, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.reason_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // int64 invitation_id = 1;
            {PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.invitation_id_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // .turms.client.model.proto.ResponseAction response_action = 2;
            {PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.response_action_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kOpenEnum)},
            // optional string reason = 3;
            {PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.reason_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{"\65\0\0\6\0\0\0\0"
          "turms.client.model.proto.UpdateGroupInvitationRequest"
          "reason"}},
    };

PROTOBUF_NOINLINE void UpdateGroupInvitationRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UpdateGroupInvitationRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.custom_attributes_.Clear();
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        _impl_.reason_.ClearNonDefaultToEmpty();
    }
    ::memset(&_impl_.invitation_id_,
             0,
             static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.response_action_) -
                                   reinterpret_cast<char*>(&_impl_.invitation_id_)) +
                 sizeof(_impl_.response_action_));
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::uint8_t* UpdateGroupInvitationRequest::_InternalSerialize(
    const MessageLite& base,
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) {
    const UpdateGroupInvitationRequest& this_ =
        static_cast<const UpdateGroupInvitationRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::uint8_t* UpdateGroupInvitationRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    const UpdateGroupInvitationRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UpdateGroupInvitationRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int64 invitation_id = 1;
    if (this_._internal_invitation_id() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this_._internal_invitation_id(), target);
    }

    // .turms.client.model.proto.ResponseAction response_action = 2;
    if (this_._internal_response_action() != 0) {
        target = stream->EnsureSpace(target);
        target =
            ::_pbi::WireFormatLite::WriteEnumToArray(2, this_._internal_response_action(), target);
    }

    cached_has_bits = this_._impl_._has_bits_[0];
    // optional string reason = 3;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this_._internal_reason();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UpdateGroupInvitationRequest.reason");
        target = stream->WriteStringMaybeAliased(3, _s, target);
    }

    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    for (unsigned i = 0, n = static_cast<unsigned>(this_._internal_custom_attributes_size()); i < n;
         i++) {
        const auto& repfield = this_._internal_custom_attributes().Get(i);
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            15, repfield, repfield.GetCachedSize(), target, stream);
    }

    if (PROTOBUF_PREDICT_FALSE(this_._internal_metadata_.have_unknown_fields())) {
        target = stream->WriteRaw(
            this_._internal_metadata_
                .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                .data(),
            static_cast<int>(
                this_._internal_metadata_
                    .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                    .size()),
            target);
    }
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UpdateGroupInvitationRequest)
    return target;
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::size_t UpdateGroupInvitationRequest::ByteSizeLong(const MessageLite& base) {
    const UpdateGroupInvitationRequest& this_ =
        static_cast<const UpdateGroupInvitationRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::size_t UpdateGroupInvitationRequest::ByteSizeLong() const {
    const UpdateGroupInvitationRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UpdateGroupInvitationRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(&this_);
    {
        // repeated .turms.client.model.proto.Value custom_attributes = 15;
        {
            total_size += 1UL * this_._internal_custom_attributes_size();
            for (const auto& msg : this_._internal_custom_attributes()) {
                total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
            }
        }
    }
    {
        // optional string reason = 3;
        cached_has_bits = this_._impl_._has_bits_[0];
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this_._internal_reason());
        }
    }
    {
        // int64 invitation_id = 1;
        if (this_._internal_invitation_id() != 0) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this_._internal_invitation_id());
        }
        // .turms.client.model.proto.ResponseAction response_action = 2;
        if (this_._internal_response_action() != 0) {
            total_size += 1 + ::_pbi::WireFormatLite::EnumSize(this_._internal_response_action());
        }
    }
    if (PROTOBUF_PREDICT_FALSE(this_._internal_metadata_.have_unknown_fields())) {
        total_size += this_._internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    this_._impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void UpdateGroupInvitationRequest::MergeImpl(::google::protobuf::MessageLite& to_msg,
                                             const ::google::protobuf::MessageLite& from_msg) {
    auto* const _this = static_cast<UpdateGroupInvitationRequest*>(&to_msg);
    auto& from = static_cast<const UpdateGroupInvitationRequest&>(from_msg);
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UpdateGroupInvitationRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        _this->_internal_set_reason(from._internal_reason());
    }
    if (from._internal_invitation_id() != 0) {
        _this->_impl_.invitation_id_ = from._impl_.invitation_id_;
    }
    if (from._internal_response_action() != 0) {
        _this->_impl_.response_action_ = from._impl_.response_action_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UpdateGroupInvitationRequest::CopyFrom(const UpdateGroupInvitationRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UpdateGroupInvitationRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void UpdateGroupInvitationRequest::InternalSwap(
    UpdateGroupInvitationRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.reason_, &other->_impl_.reason_, arena);
    ::google::protobuf::internal::memswap<
        PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.response_action_) +
        sizeof(UpdateGroupInvitationRequest::_impl_.response_action_) -
        PROTOBUF_FIELD_OFFSET(UpdateGroupInvitationRequest, _impl_.invitation_id_)>(
        reinterpret_cast<char*>(&_impl_.invitation_id_),
        reinterpret_cast<char*>(&other->_impl_.invitation_id_));
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace google {
namespace protobuf {}  // namespace protobuf
}  // namespace google
// @@protoc_insertion_point(global_scope)
#include "google/protobuf/port_undef.inc"