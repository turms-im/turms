// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: model/storage/storage_resource_infos.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/model/storage/storage_resource_infos.pb.h"

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

inline constexpr StorageResourceInfos::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : infos_{},
      _cached_size_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR StorageResourceInfos::StorageResourceInfos(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct StorageResourceInfosDefaultTypeInternal {
    PROTOBUF_CONSTEXPR StorageResourceInfosDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~StorageResourceInfosDefaultTypeInternal() {
    }
    union {
        StorageResourceInfos _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    StorageResourceInfosDefaultTypeInternal _StorageResourceInfos_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class StorageResourceInfos::_Internal {
   public:
};

void StorageResourceInfos::clear_infos() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.infos_.Clear();
}
StorageResourceInfos::StorageResourceInfos(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.StorageResourceInfos)
}
inline PROTOBUF_NDEBUG_INLINE StorageResourceInfos::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::StorageResourceInfos& from_msg)
    : infos_{visibility, arena, from.infos_},
      _cached_size_{0} {
}

StorageResourceInfos::StorageResourceInfos(::google::protobuf::Arena* arena,
                                           const StorageResourceInfos& from)
    : ::google::protobuf::MessageLite(arena) {
    StorageResourceInfos* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.StorageResourceInfos)
}
inline PROTOBUF_NDEBUG_INLINE StorageResourceInfos::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : infos_{visibility, arena},
      _cached_size_{0} {
}

inline void StorageResourceInfos::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
}
StorageResourceInfos::~StorageResourceInfos() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.StorageResourceInfos)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void StorageResourceInfos::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* StorageResourceInfos::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<46> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(StorageResourceInfos, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.StorageResourceInfos",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<0, 1, 1, 0, 2>
    StorageResourceInfos::_table_ = {
        {
            0,  // no _has_bits_
            0,  // no _extensions_
            1,
            0,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294967294,  // skipmap
            offsetof(decltype(_table_), field_entries),
            1,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_StorageResourceInfos_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::StorageResourceInfos>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            // repeated .turms.client.model.proto.StorageResourceInfo infos = 1;
            {::_pbi::TcParser::FastMtR1,
             {10, 63, 0, PROTOBUF_FIELD_OFFSET(StorageResourceInfos, _impl_.infos_)}},
        }},
        {{65535, 65535}},
        {{
            // repeated .turms.client.model.proto.StorageResourceInfo infos = 1;
            {PROTOBUF_FIELD_OFFSET(StorageResourceInfos, _impl_.infos_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::StorageResourceInfo>()},
        }},
        {{}},
};

PROTOBUF_NOINLINE void StorageResourceInfos::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.StorageResourceInfos)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.infos_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* StorageResourceInfos::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.StorageResourceInfos)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // repeated .turms.client.model.proto.StorageResourceInfo infos = 1;
    for (unsigned i = 0, n = static_cast<unsigned>(this->_internal_infos_size()); i < n; i++) {
        const auto& repfield = this->_internal_infos().Get(i);
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            1, repfield, repfield.GetCachedSize(), target, stream);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.StorageResourceInfos)
    return target;
}

::size_t StorageResourceInfos::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.StorageResourceInfos)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    // repeated .turms.client.model.proto.StorageResourceInfo infos = 1;
    total_size += 1UL * this->_internal_infos_size();
    for (const auto& msg : this->_internal_infos()) {
        total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
    }
    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void StorageResourceInfos::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const StorageResourceInfos*>(&from));
}

void StorageResourceInfos::MergeFrom(const StorageResourceInfos& from) {
    StorageResourceInfos* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.StorageResourceInfos)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_infos()->MergeFrom(from._internal_infos());
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void StorageResourceInfos::CopyFrom(const StorageResourceInfos& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.StorageResourceInfos)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void StorageResourceInfos::InternalSwap(StorageResourceInfos* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    _impl_.infos_.InternalSwap(&other->_impl_.infos_);
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