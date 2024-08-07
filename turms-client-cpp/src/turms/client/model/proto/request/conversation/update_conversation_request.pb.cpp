// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/conversation/update_conversation_request.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/request/conversation/update_conversation_request.pb.h"

#include <algorithm>
#include <type_traits>

#include "google/protobuf/extension_set.h"
#include "google/protobuf/generated_message_tctable_impl.h"
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

inline constexpr UpdateConversationRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      user_id_{::int64_t{0}},
      group_id_{::int64_t{0}},
      read_date_{::int64_t{0}} {
}

template <typename>
PROTOBUF_CONSTEXPR UpdateConversationRequest::UpdateConversationRequest(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct UpdateConversationRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UpdateConversationRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UpdateConversationRequestDefaultTypeInternal() {
    }
    union {
        UpdateConversationRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UpdateConversationRequestDefaultTypeInternal _UpdateConversationRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UpdateConversationRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<UpdateConversationRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_._has_bits_);
};

void UpdateConversationRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
UpdateConversationRequest::UpdateConversationRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UpdateConversationRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateConversationRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::UpdateConversationRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_} {
}

UpdateConversationRequest::UpdateConversationRequest(::google::protobuf::Arena* arena,
                                                     const UpdateConversationRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    UpdateConversationRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, user_id_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, user_id_),
             offsetof(Impl_, read_date_) - offsetof(Impl_, user_id_) + sizeof(Impl_::read_date_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UpdateConversationRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateConversationRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena} {
}

inline void UpdateConversationRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, user_id_),
             0,
             offsetof(Impl_, read_date_) - offsetof(Impl_, user_id_) + sizeof(Impl_::read_date_));
}
UpdateConversationRequest::~UpdateConversationRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UpdateConversationRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void UpdateConversationRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* UpdateConversationRequest::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<51> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.UpdateConversationRequest",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 4, 1, 0, 2>
    UpdateConversationRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950904,  // skipmap
            offsetof(decltype(_table_), field_entries),
            4,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_UpdateConversationRequest_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UpdateConversationRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // optional int64 user_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 0, 0, PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.user_id_)}},
            // optional int64 group_id = 2;
            {::_pbi::TcParser::FastV64S1,
             {16, 1, 0, PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.group_id_)}},
            // int64 read_date = 3;
            {::_pbi::TcParser::FastV64S1,
             {24, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.read_date_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // optional int64 user_id = 1;
            {PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.user_id_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 group_id = 2;
            {PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.group_id_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // int64 read_date = 3;
            {PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.read_date_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{}},
};

PROTOBUF_NOINLINE void UpdateConversationRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UpdateConversationRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.custom_attributes_.Clear();
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        ::memset(&_impl_.user_id_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.group_id_) -
                                       reinterpret_cast<char*>(&_impl_.user_id_)) +
                     sizeof(_impl_.group_id_));
    }
    _impl_.read_date_ = ::int64_t{0};
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* UpdateConversationRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UpdateConversationRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    // optional int64 user_id = 1;
    if (cached_has_bits & 0x00000001u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_user_id(), target);
    }

    // optional int64 group_id = 2;
    if (cached_has_bits & 0x00000002u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<2>(
            stream, this->_internal_group_id(), target);
    }

    // int64 read_date = 3;
    if (this->_internal_read_date() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<3>(
            stream, this->_internal_read_date(), target);
    }

    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    for (unsigned i = 0, n = static_cast<unsigned>(this->_internal_custom_attributes_size()); i < n;
         i++) {
        const auto& repfield = this->_internal_custom_attributes().Get(i);
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            15, repfield, repfield.GetCachedSize(), target, stream);
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        target = stream->WriteRaw(
            _internal_metadata_
                .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                .data(),
            static_cast<int>(
                _internal_metadata_
                    .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                    .size()),
            target);
    }
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UpdateConversationRequest)
    return target;
}

::size_t UpdateConversationRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UpdateConversationRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    total_size += 1UL * this->_internal_custom_attributes_size();
    for (const auto& msg : this->_internal_custom_attributes()) {
        total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
    }
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        // optional int64 user_id = 1;
        if (cached_has_bits & 0x00000001u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_user_id());
        }

        // optional int64 group_id = 2;
        if (cached_has_bits & 0x00000002u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_group_id());
        }
    }
    // int64 read_date = 3;
    if (this->_internal_read_date() != 0) {
        total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_read_date());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void UpdateConversationRequest::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const UpdateConversationRequest*>(&from));
}

void UpdateConversationRequest::MergeFrom(const UpdateConversationRequest& from) {
    UpdateConversationRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UpdateConversationRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        if (cached_has_bits & 0x00000001u) {
            _this->_impl_.user_id_ = from._impl_.user_id_;
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_impl_.group_id_ = from._impl_.group_id_;
        }
    }
    if (from._internal_read_date() != 0) {
        _this->_impl_.read_date_ = from._impl_.read_date_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UpdateConversationRequest::CopyFrom(const UpdateConversationRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UpdateConversationRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void UpdateConversationRequest::InternalSwap(UpdateConversationRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::google::protobuf::internal::memswap<
        PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.read_date_) +
        sizeof(UpdateConversationRequest::_impl_.read_date_) -
        PROTOBUF_FIELD_OFFSET(UpdateConversationRequest, _impl_.user_id_)>(
        reinterpret_cast<char*>(&_impl_.user_id_),
        reinterpret_cast<char*>(&other->_impl_.user_id_));
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
