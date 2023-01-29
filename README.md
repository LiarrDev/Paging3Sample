# Paging3Sample

本项目基于 [@郭霖](https://github.com/guolindev) 的 [Paging3Sample](https://github.com/guolindev/Paging3Sample) 改造。参考[官方文档](https://developer.android.google.cn/topic/libraries/architecture/paging/v3-overview)提供的架构分层逻辑。

官方对于分层的描述如下图：

![](https://developer.android.google.cn/static/topic/libraries/architecture/images/paging3-library-architecture.svg)
![](https://developer.android.google.cn/static/codelabs/android-paging-basics/img/566d0f6506f39480_1920.jpeg)

[@郭霖](https://github.com/guolindev) 的 [Paging3Sample](https://github.com/guolindev/Paging3Sample) 项目中 `Pager` 和 `Flow<PagingData` 都交给了 Repository 层构建，而 ViewModel 层只负责缓存，似乎有些职责混乱。

本项目重新整理了层级职责，更加符合 MVVM 架构。