# FetchContent_MakeAvailable requires 3.14
cmake_minimum_required(VERSION 3.14)

# ---- Project ----

project(
    turms_client
    VERSION 0.10.0
    LANGUAGES CXX
)

# ---- Options ----

#option(TURMS_CLIENT_BUILD_TESTS "Build Turms client tests" OFF)
#option(TURMS_CLIENT_BUILD_EXAMPLES "Build examples" OFF)

# ---- Top-level CMake config ----

set(CMAKE_CXX_STANDARD 17)

add_compile_definitions(
    BOOST_THREAD_PROVIDES_FUTURE
    BOOST_THREAD_PROVIDES_FUTURE_CONTINUATION
    BOOST_THREAD_PROVIDES_FUTURE_UNWRAP
)

# ---- Dependencies ----

# Prefer config mode as config files are typically
# installed as part of the package, and the config mode is more reliable.
find_package(absl CONFIG REQUIRED)
find_package(Protobuf CONFIG REQUIRED)
find_package(Boost CONFIG REQUIRED COMPONENTS asio chrono core thread)

get_target_property(absl_INCLUDE_DIRS absl::base INTERFACE_INCLUDE_DIRECTORIES)

# ---- Source files ----

file(
    GLOB_RECURSE headers
    CONFIGURE_DEPENDS "${CMAKE_CURRENT_SOURCE_DIR}/include/**/*.h"
)
file(
    GLOB_RECURSE sources
    CONFIGURE_DEPENDS "${CMAKE_CURRENT_SOURCE_DIR}/src/**/*.cpp"
)

# ---- Create library ----

add_library(
    ${PROJECT_NAME}
    ${headers}
    ${sources}
)

if (NOT absl_INCLUDE_DIRS)
    message(FATAL_ERROR "The variable absl_INCLUDE_DIRS is not set")
endif ()
if (NOT Protobuf_INCLUDE_DIRS)
    message(FATAL_ERROR "The variable Protobuf_INCLUDE_DIRS is not set")
endif ()
if (NOT Boost_INCLUDE_DIRS)
    message(FATAL_ERROR "The variable Boost_INCLUDE_DIRS is not set")
endif ()

target_include_directories(
    ${PROJECT_NAME}
    PUBLIC
    include
    ${absl_INCLUDE_DIRS}
    ${Protobuf_INCLUDE_DIRS}
    ${Boost_INCLUDE_DIRS}
)