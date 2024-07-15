// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/storage/delete_resource_request.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/request/storage/delete_resource_request.pb.h"

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

inline constexpr DeleteResourceRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      id_str_(&::google::protobuf::internal::fixed_address_empty_string,
              ::_pbi::ConstantInitialized()),
      id_num_{::int64_t{0}},
      type_{static_cast<::turms::client::model::proto::StorageResourceType>(0)} {
}

template <typename>
PROTOBUF_CONSTEXPR DeleteResourceRequest::DeleteResourceRequest(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct DeleteResourceRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR DeleteResourceRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~DeleteResourceRequestDefaultTypeInternal() {
    }
    union {
        DeleteResourceRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    DeleteResourceRequestDefaultTypeInternal _DeleteResourceRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class DeleteResourceRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<DeleteResourceRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_._has_bits_);
};

void DeleteResourceRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
DeleteResourceRequest::DeleteResourceRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.DeleteResourceRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteResourceRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::DeleteResourceRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      id_str_(arena, from.id_str_) {
}

DeleteResourceRequest::DeleteResourceRequest(::google::protobuf::Arena* arena,
                                             const DeleteResourceRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    DeleteResourceRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_num_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, id_num_),
             offsetof(Impl_, type_) - offsetof(Impl_, id_num_) + sizeof(Impl_::type_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.DeleteResourceRequest)
}
inline PROTOBUF_NDEBUG_INLINE DeleteResourceRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena},
      id_str_(arena) {
}

inline void DeleteResourceRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_num_),
             0,
             offsetof(Impl_, type_) - offsetof(Impl_, id_num_) + sizeof(Impl_::type_));
}
DeleteResourceRequest::~DeleteResourceRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.DeleteResourceRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void DeleteResourceRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.id_str_.Destroy();
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* DeleteResourceRequest::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<47> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.DeleteResourceRequest",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 4, 1, 61, 2>
    DeleteResourceRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950904,  // skipmap
            offsetof(decltype(_table_), field_entries),
            4,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_DeleteResourceRequest_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::DeleteResourceRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // .turms.client.model.proto.StorageResourceType type = 1;
            {::_pbi::TcParser::FastV32S1,
             {8, 63, 0, PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.type_)}},
            // optional int64 id_num = 2;
            {::_pbi::TcParser::FastV64S1,
             {16, 1, 0, PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.id_num_)}},
            // optional string id_str = 3;
            {::_pbi::TcParser::FastUS1,
             {26, 0, 0, PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.id_str_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122, 63, 0, PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // .turms.client.model.proto.StorageResourceType type = 1;
            {PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.type_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kOpenEnum)},
            // optional int64 id_num = 2;
            {PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.id_num_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional string id_str = 3;
            {PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.id_str_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{"\56\0\0\6\0\0\0\0"
          "turms.client.model.proto.DeleteResourceRequest"
          "id_str"}},
};

PROTOBUF_NOINLINE void DeleteResourceRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.DeleteResourceRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.custom_attributes_.Clear();
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        _impl_.id_str_.ClearNonDefaultToEmpty();
    }
    _impl_.id_num_ = ::int64_t{0};
    _impl_.type_ = 0;
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* DeleteResourceRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.DeleteResourceRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // .turms.client.model.proto.StorageResourceType type = 1;
    if (this->_internal_type() != 0) {
        target = stream->EnsureSpace(target);
        target = ::_pbi::WireFormatLite::WriteEnumToArray(1, this->_internal_type(), target);
    }

    cached_has_bits = _impl_._has_bits_[0];
    // optional int64 id_num = 2;
    if (cached_has_bits & 0x00000002u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<2>(
            stream, this->_internal_id_num(), target);
    }

    // optional string id_str = 3;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this->_internal_id_str();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.DeleteResourceRequest.id_str");
        target = stream->WriteStringMaybeAliased(3, _s, target);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.DeleteResourceRequest)
    return target;
}

::size_t DeleteResourceRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.DeleteResourceRequest)
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
        // optional string id_str = 3;
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_id_str());
        }

        // optional int64 id_num = 2;
        if (cached_has_bits & 0x00000002u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_id_num());
        }
    }
    // .turms.client.model.proto.StorageResourceType type = 1;
    if (this->_internal_type() != 0) {
        total_size += 1 + ::_pbi::WireFormatLite::EnumSize(this->_internal_type());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void DeleteResourceRequest::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const DeleteResourceRequest*>(&from));
}

void DeleteResourceRequest::MergeFrom(const DeleteResourceRequest& from) {
    DeleteResourceRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.DeleteResourceRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        if (cached_has_bits & 0x00000001u) {
            _this->_internal_set_id_str(from._internal_id_str());
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_impl_.id_num_ = from._impl_.id_num_;
        }
    }
    if (from._internal_type() != 0) {
        _this->_impl_.type_ = from._impl_.type_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void DeleteResourceRequest::CopyFrom(const DeleteResourceRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.DeleteResourceRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void DeleteResourceRequest::InternalSwap(DeleteResourceRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.id_str_, &other->_impl_.id_str_, arena);
    ::google::protobuf::internal::memswap<
        PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.type_) +
        sizeof(DeleteResourceRequest::_impl_.type_) -
        PROTOBUF_FIELD_OFFSET(DeleteResourceRequest, _impl_.id_num_)>(
        reinterpret_cast<char*>(&_impl_.id_num_), reinterpret_cast<char*>(&other->_impl_.id_num_));
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
