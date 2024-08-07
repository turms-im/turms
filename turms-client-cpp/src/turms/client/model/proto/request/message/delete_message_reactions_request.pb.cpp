// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/message/delete_message_reactions_request.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/request/message/delete_message_reactions_request.pb.h"

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

inline constexpr DeleteMessageReactionsRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : reaction_types_{},
      _reaction_types_cached_byte_size_{0},
      custom_attributes_{},
      message_id_{::int64_t{0}},
      _cached_size_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR DeleteMessageReactionsRequest::DeleteMessageReactionsRequest(
    ::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct DeleteMessageReactionsRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR DeleteMessageReactionsRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~DeleteMessageReactionsRequestDefaultTypeInternal() {
    }
    union {
        DeleteMessageReactionsRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    DeleteMessageReactionsRequestDefaultTypeInternal
        _DeleteMessageReactionsRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class DeleteMessageReactionsRequest::_Internal {
   public:
};

void DeleteMessageReactionsRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
DeleteMessageReactionsRequest::DeleteMessageReactionsRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.DeleteMessageReactionsRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteMessageReactionsRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::DeleteMessageReactionsRequest& from_msg)
    : reaction_types_{visibility, arena, from.reaction_types_},
      _reaction_types_cached_byte_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      _cached_size_{0} {
}

DeleteMessageReactionsRequest::DeleteMessageReactionsRequest(
    ::google::protobuf::Arena* arena, const DeleteMessageReactionsRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    DeleteMessageReactionsRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    _impl_.message_id_ = from._impl_.message_id_;

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.DeleteMessageReactionsRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteMessageReactionsRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : reaction_types_{visibility, arena},
      _reaction_types_cached_byte_size_{0},
      custom_attributes_{visibility, arena},
      _cached_size_{0} {
}

inline void DeleteMessageReactionsRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    _impl_.message_id_ = {};
}
DeleteMessageReactionsRequest::~DeleteMessageReactionsRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.DeleteMessageReactionsRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void DeleteMessageReactionsRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* DeleteMessageReactionsRequest::GetClassData()
    const {
    PROTOBUF_CONSTINIT static const ClassDataLite<55> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.DeleteMessageReactionsRequest",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<2, 3, 1, 0, 2>
    DeleteMessageReactionsRequest::_table_ = {
        {
            0,  // no _has_bits_
            0,  // no _extensions_
            15,
            24,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950908,  // skipmap
            offsetof(decltype(_table_), field_entries),
            3,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_DeleteMessageReactionsRequest_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::DeleteMessageReactionsRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // int64 message_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 63, 0, PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.message_id_)}},
            // repeated int32 reaction_types = 2;
            {::_pbi::TcParser::FastV32P1,
             {18,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.reaction_types_)}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // int64 message_id = 1;
            {PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.message_id_),
             0,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // repeated int32 reaction_types = 2;
            {PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.reaction_types_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kPackedInt32)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(DeleteMessageReactionsRequest, _impl_.custom_attributes_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{}},
};

PROTOBUF_NOINLINE void DeleteMessageReactionsRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.DeleteMessageReactionsRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.reaction_types_.Clear();
    _impl_.custom_attributes_.Clear();
    _impl_.message_id_ = ::int64_t{0};
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* DeleteMessageReactionsRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.DeleteMessageReactionsRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int64 message_id = 1;
    if (this->_internal_message_id() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_message_id(), target);
    }

    // repeated int32 reaction_types = 2;
    {
        int byte_size = _impl_._reaction_types_cached_byte_size_.Get();
        if (byte_size > 0) {
            target = stream->WriteInt32Packed(2, _internal_reaction_types(), byte_size, target);
        }
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.DeleteMessageReactionsRequest)
    return target;
}

::size_t DeleteMessageReactionsRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.DeleteMessageReactionsRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    // repeated int32 reaction_types = 2;
    {
        std::size_t data_size = ::_pbi::WireFormatLite::Int32Size(this->_internal_reaction_types());
        _impl_._reaction_types_cached_byte_size_.Set(::_pbi::ToCachedSize(data_size));
        std::size_t tag_size =
            data_size == 0 ? 0
                           : 1 + ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
        total_size += tag_size + data_size;
    }
    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    total_size += 1UL * this->_internal_custom_attributes_size();
    for (const auto& msg : this->_internal_custom_attributes()) {
        total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
    }
    // int64 message_id = 1;
    if (this->_internal_message_id() != 0) {
        total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_message_id());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void DeleteMessageReactionsRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const DeleteMessageReactionsRequest*>(&from));
}

void DeleteMessageReactionsRequest::MergeFrom(const DeleteMessageReactionsRequest& from) {
    DeleteMessageReactionsRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.DeleteMessageReactionsRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_reaction_types()->MergeFrom(from._internal_reaction_types());
    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    if (from._internal_message_id() != 0) {
        _this->_impl_.message_id_ = from._impl_.message_id_;
    }
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void DeleteMessageReactionsRequest::CopyFrom(const DeleteMessageReactionsRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.DeleteMessageReactionsRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void DeleteMessageReactionsRequest::InternalSwap(
    DeleteMessageReactionsRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    _impl_.reaction_types_.InternalSwap(&other->_impl_.reaction_types_);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    swap(_impl_.message_id_, other->_impl_.message_id_);
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
