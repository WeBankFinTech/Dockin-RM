/*
 * Copyright (C) @2021 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.webank.dockin.rm.bean;
import org.springframework.util.Assert;
public class PageInfo {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String MAP_KEY = "page";
    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected int currentPage = 0;
    protected int totalPage = -1;
    protected int totalCount = -1;
    public PageInfo() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }
    public PageInfo(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        Assert.isTrue(pageSize >= 1, "page size must be larger than 1");
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        Assert.isTrue(pageSize >= 1, "page size must be larger than 1");
        this.pageSize = pageSize;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        Assert.isTrue(pageSize >= 1, "current page must be larger than 1");
        this.currentPage = currentPage;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        Assert.isTrue(pageSize >= 0, "total page must be larger than or equals to 0");
        this.totalPage = totalPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        Assert.isTrue(pageSize >= 0, "total count must be larger than or equals to 0");
        this.totalCount = totalCount;
    }
}
