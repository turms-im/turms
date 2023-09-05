# FetchContent_MakeAvailable requires 3.14
cmake_minimum_required(VERSION 3.14)

# ---- Project ----

project(
    turms_client
    VERSION 0.10.0
    LANGUAGES CXX
)

message(STATUS "TODO: Not implemented")

# ---- Options ----

#option(TURMS_CLIENT_BUILD_TESTS "Build Turms client tests" OFF)
#option(TURMS_CLIENT_BUILD_EXAMPLES "Build examples" OFF)

# ---- Top-level CMake config ----

set(CMAKE_CXX_STANDARD 14)

add_compile_definitions(
        BOOST_THREAD_PROVIDES_FUTURE
        BOOST_THREAD_PROVIDES_FUTURE_CONTINUATION
        BOOST_THREAD_PROVIDES_FUTURE_UNWRAP
)

# ---- Dependencies ----

find_package(absl REQUIRED)
find_package(Protobuf REQUIRED)
find_package(Boost REQUIRED)

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
target_include_directories(
    ${PROJECT_NAME}
    PUBLIC
    include
    ${absl_INCLUDE_DIR}
    ${Protobuf_INCLUDE_DIR}
    ${Boost_INCLUDE_DIR}
)