// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/group/enrollment/create_group_join_request_request.proto
// Protobuf C++ Version: 5.26.1

#include "turms/client/model/proto/request/group/enrollment/create_group_join_request_request.pb.h"

#include <algorithm>

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

inline constexpr CreateGroupJoinRequestRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : content_(&::google::protobuf::internal::fixed_address_empty_string,
               ::_pbi::ConstantInitialized()),
      group_id_{::int64_t{0}},
      _cached_size_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR CreateGroupJoinRequestRequest::CreateGroupJoinRequestRequest(
    ::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct CreateGroupJoinRequestRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR CreateGroupJoinRequestRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~CreateGroupJoinRequestRequestDefaultTypeInternal() {
    }
    union {
        CreateGroupJoinRequestRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    CreateGroupJoinRequestRequestDefaultTypeInternal
        _CreateGroupJoinRequestRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class CreateGroupJoinRequestRequest::_Internal {
   public:
};

CreateGroupJoinRequestRequest::CreateGroupJoinRequestRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.CreateGroupJoinRequestRequest)
}
inline PROTOBUF_NDEBUG_INLINE CreateGroupJoinRequestRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from)
    : content_(arena, from.content_),
      _cached_size_{0} {
}

CreateGroupJoinRequestRequest::CreateGroupJoinRequestRequest(
    ::google::protobuf::Arena* arena, const CreateGroupJoinRequestRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    CreateGroupJoinRequestRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_);
    _impl_.group_id_ = from._impl_.group_id_;

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.CreateGroupJoinRequestRequest)
}
inline PROTOBUF_NDEBUG_INLINE CreateGroupJoinRequestRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : content_(arena),
      _cached_size_{0} {
}

inline void CreateGroupJoinRequestRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    _impl_.group_id_ = {};
}
CreateGroupJoinRequestRequest::~CreateGroupJoinRequestRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.CreateGroupJoinRequestRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void CreateGroupJoinRequestRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.content_.Destroy();
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* CreateGroupJoinRequestRequest::GetClassData()
    const {
    struct ClassData_ {
        ::google::protobuf::MessageLite::ClassData header;
        char type_name[55];
    };

    PROTOBUF_CONSTINIT static const ClassData_ _data_ = {
        {
            nullptr,  // OnDemandRegisterArenaDtor
            PROTOBUF_FIELD_OFFSET(CreateGroupJoinRequestRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.CreateGroupJoinRequestRequest",
    };

    return &_data_.header;
}
PROTOBUF_NOINLINE void CreateGroupJoinRequestRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.CreateGroupJoinRequestRequest)
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.content_.ClearToEmpty();
    _impl_.group_id_ = ::int64_t{0};
    _internal_metadata_.Clear<std::string>();
}

const char* CreateGroupJoinRequestRequest::_InternalParse(const char* ptr,
                                                          ::_pbi::ParseContext* ctx) {
    ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
    return ptr;
}

PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<1, 2, 0, 70, 2>
    CreateGroupJoinRequestRequest::_table_ = {
        {
            0,  // no _has_bits_
            0,  // no _extensions_
            2,
            8,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294967292,  // skipmap
            offsetof(decltype(_table_), field_entries),
            2,                                         // num_field_entries
            0,                                         // num_aux_entries
            offsetof(decltype(_table_), field_names),  // no aux_entries
            &_CreateGroupJoinRequestRequest_default_instance_._instance,
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::CreateGroupJoinRequestRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            // string content = 2;
            {::_pbi::TcParser::FastUS1,
             {18, 63, 0, PROTOBUF_FIELD_OFFSET(CreateGroupJoinRequestRequest, _impl_.content_)}},
            // int64 group_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 63, 0, PROTOBUF_FIELD_OFFSET(CreateGroupJoinRequestRequest, _impl_.group_id_)}},
        }},
        {{65535, 65535}},
        {{
            // int64 group_id = 1;
            {PROTOBUF_FIELD_OFFSET(CreateGroupJoinRequestRequest, _impl_.group_id_),
             0,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // string content = 2;
            {PROTOBUF_FIELD_OFFSET(CreateGroupJoinRequestRequest, _impl_.content_),
             0,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kUtf8String | ::_fl::kRepAString)},
        }},
        // no aux_entries
        {{"\66\0\7\0\0\0\0\0"
          "turms.client.model.proto.CreateGroupJoinRequestRequest"
          "content"}},
};

::uint8_t* CreateGroupJoinRequestRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.CreateGroupJoinRequestRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int64 group_id = 1;
    if (this->_internal_group_id() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_group_id(), target);
    }

    // string content = 2;
    if (!this->_internal_content().empty()) {
        const std::string& _s = this->_internal_content();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.CreateGroupJoinRequestRequest.content");
        target = stream->WriteStringMaybeAliased(2, _s, target);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.CreateGroupJoinRequestRequest)
    return target;
}

::size_t CreateGroupJoinRequestRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.CreateGroupJoinRequestRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    // string content = 2;
    if (!this->_internal_content().empty()) {
        total_size +=
            1 + ::google::protobuf::internal::WireFormatLite::StringSize(this->_internal_content());
    }

    // int64 group_id = 1;
    if (this->_internal_group_id() != 0) {
        total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_group_id());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void CreateGroupJoinRequestRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const CreateGroupJoinRequestRequest*>(&from));
}

void CreateGroupJoinRequestRequest::MergeFrom(const CreateGroupJoinRequestRequest& from) {
    CreateGroupJoinRequestRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.CreateGroupJoinRequestRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    if (!from._internal_content().empty()) {
        _this->_internal_set_content(from._internal_content());
    }
    if (from._internal_group_id() != 0) {
        _this->_impl_.group_id_ = from._impl_.group_id_;
    }
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void CreateGroupJoinRequestRequest::CopyFrom(const CreateGroupJoinRequestRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.CreateGroupJoinRequestRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

PROTOBUF_NOINLINE bool CreateGroupJoinRequestRequest::IsInitialized() const {
    return true;
}

void CreateGroupJoinRequestRequest::InternalSwap(
    CreateGroupJoinRequestRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.content_, &other->_impl_.content_, arena);
    swap(_impl_.group_id_, other->_impl_.group_id_);
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