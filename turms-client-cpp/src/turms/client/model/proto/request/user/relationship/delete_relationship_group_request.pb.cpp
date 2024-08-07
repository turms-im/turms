// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/user/relationship/delete_relationship_group_request.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/request/user/relationship/delete_relationship_group_request.pb.h"

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

inline constexpr DeleteRelationshipGroupRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      group_index_{0},
      target_group_index_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR DeleteRelationshipGroupRequest::DeleteRelationshipGroupRequest(
    ::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct DeleteRelationshipGroupRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR DeleteRelationshipGroupRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~DeleteRelationshipGroupRequestDefaultTypeInternal() {
    }
    union {
        DeleteRelationshipGroupRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    DeleteRelationshipGroupRequestDefaultTypeInternal
        _DeleteRelationshipGroupRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class DeleteRelationshipGroupRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<DeleteRelationshipGroupRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_._has_bits_);
};

void DeleteRelationshipGroupRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
DeleteRelationshipGroupRequest::DeleteRelationshipGroupRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.DeleteRelationshipGroupRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteRelationshipGroupRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::DeleteRelationshipGroupRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_} {
}

DeleteRelationshipGroupRequest::DeleteRelationshipGroupRequest(
    ::google::protobuf::Arena* arena, const DeleteRelationshipGroupRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    DeleteRelationshipGroupRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, group_index_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, group_index_),
             offsetof(Impl_, target_group_index_) - offsetof(Impl_, group_index_) +
                 sizeof(Impl_::target_group_index_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.DeleteRelationshipGroupRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteRelationshipGroupRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena} {
}

inline void DeleteRelationshipGroupRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, group_index_),
             0,
             offsetof(Impl_, target_group_index_) - offsetof(Impl_, group_index_) +
                 sizeof(Impl_::target_group_index_));
}
DeleteRelationshipGroupRequest::~DeleteRelationshipGroupRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.DeleteRelationshipGroupRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void DeleteRelationshipGroupRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* DeleteRelationshipGroupRequest::GetClassData()
    const {
    PROTOBUF_CONSTINIT static const ClassDataLite<56> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.DeleteRelationshipGroupRequest",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<2, 3, 1, 0, 2>
    DeleteRelationshipGroupRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            24,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950908,  // skipmap
            offsetof(decltype(_table_), field_entries),
            3,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_DeleteRelationshipGroupRequest_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::DeleteRelationshipGroupRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // int32 group_index = 1;
            {::_pbi::TcParser::FastV32S1,
             {8,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.group_index_)}},
            // optional int32 target_group_index = 2;
            {::_pbi::TcParser::FastV32S1,
             {16,
              0,
              0,
              PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.target_group_index_)}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // int32 group_index = 1;
            {PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.group_index_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt32)},
            // optional int32 target_group_index = 2;
            {PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.target_group_index_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt32)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{}},
};

PROTOBUF_NOINLINE void DeleteRelationshipGroupRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.DeleteRelationshipGroupRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.custom_attributes_.Clear();
    _impl_.group_index_ = 0;
    _impl_.target_group_index_ = 0;
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* DeleteRelationshipGroupRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.DeleteRelationshipGroupRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int32 group_index = 1;
    if (this->_internal_group_index() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArrayWithField<1>(
            stream, this->_internal_group_index(), target);
    }

    cached_has_bits = _impl_._has_bits_[0];
    // optional int32 target_group_index = 2;
    if (cached_has_bits & 0x00000001u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArrayWithField<2>(
            stream, this->_internal_target_group_index(), target);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.DeleteRelationshipGroupRequest)
    return target;
}

::size_t DeleteRelationshipGroupRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.DeleteRelationshipGroupRequest)
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
    // int32 group_index = 1;
    if (this->_internal_group_index() != 0) {
        total_size += ::_pbi::WireFormatLite::Int32SizePlusOne(this->_internal_group_index());
    }

    // optional int32 target_group_index = 2;
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        total_size +=
            ::_pbi::WireFormatLite::Int32SizePlusOne(this->_internal_target_group_index());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void DeleteRelationshipGroupRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const DeleteRelationshipGroupRequest*>(&from));
}

void DeleteRelationshipGroupRequest::MergeFrom(const DeleteRelationshipGroupRequest& from) {
    DeleteRelationshipGroupRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.DeleteRelationshipGroupRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    if (from._internal_group_index() != 0) {
        _this->_impl_.group_index_ = from._impl_.group_index_;
    }
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        _this->_impl_.target_group_index_ = from._impl_.target_group_index_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void DeleteRelationshipGroupRequest::CopyFrom(const DeleteRelationshipGroupRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.DeleteRelationshipGroupRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void DeleteRelationshipGroupRequest::InternalSwap(
    DeleteRelationshipGroupRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::google::protobuf::internal::memswap<
        PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.target_group_index_) +
        sizeof(DeleteRelationshipGroupRequest::_impl_.target_group_index_) -
        PROTOBUF_FIELD_OFFSET(DeleteRelationshipGroupRequest, _impl_.group_index_)>(
        reinterpret_cast<char*>(&_impl_.group_index_),
        reinterpret_cast<char*>(&other->_impl_.group_index_));
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