// Generated by the protocol buffer compiler.  DO NOT EDIT!

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "gutil/proto/types.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace common {

namespace {

const ::google::protobuf::EnumDescriptor* DataType_descriptor_ = NULL;

}  // namespace


void protobuf_AssignDesc_gutil_2fproto_2ftypes_2eproto() {
  protobuf_AddDesc_gutil_2fproto_2ftypes_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "gutil/proto/types.proto");
  GOOGLE_CHECK(file != NULL);
  DataType_descriptor_ = file->enum_type(0);
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
inline void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_gutil_2fproto_2ftypes_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
}

}  // namespace

void protobuf_ShutdownFile_gutil_2fproto_2ftypes_2eproto() {
}

void protobuf_AddDesc_gutil_2fproto_2ftypes_2eproto() {
  static bool already_here = false;
  if (already_here) return;
  already_here = true;
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\027gutil/proto/types.proto\022\006common*\247\001\n\010Da"
    "taType\022\t\n\005INT32\020\001\022\t\n\005INT64\020\002\022\n\n\006UINT32\020\010"
    "\022\n\n\006UINT64\020\003\022\t\n\005FLOAT\020\t\022\n\n\006DOUBLE\020\005\022\010\n\004B"
    "OOL\020\006\022\010\n\004DATE\020\n\022\014\n\010DATETIME\020\004\022\n\n\006STRING\020"
    "\000\022\n\n\006BINARY\020\007\022\r\n\tDATA_TYPE\020\013\022\r\n\tNULL_TYP"
    "E\020\014B4\n%com.google.datawarehouse.common.p"
    "rotoB\013CommonEnums", 257);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "gutil/proto/types.proto", &protobuf_RegisterTypes);
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_gutil_2fproto_2ftypes_2eproto);
}

// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_gutil_2fproto_2ftypes_2eproto {
  StaticDescriptorInitializer_gutil_2fproto_2ftypes_2eproto() {
    protobuf_AddDesc_gutil_2fproto_2ftypes_2eproto();
  }
} static_descriptor_initializer_gutil_2fproto_2ftypes_2eproto_;

const ::google::protobuf::EnumDescriptor* DataType_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return DataType_descriptor_;
}
bool DataType_IsValid(int value) {
  switch(value) {
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
      return true;
    default:
      return false;
  }
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace common

// @@protoc_insertion_point(global_scope)
