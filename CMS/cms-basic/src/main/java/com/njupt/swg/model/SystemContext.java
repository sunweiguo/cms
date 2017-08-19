package com.njupt.swg.model;

public class SystemContext {
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	private static ThreadLocal<String> realPath = new ThreadLocal<String>();
	public static String getRealPath() {
		return realPath.get();
	}
	public static void setRealPath(String _realPath) {
		realPath.set(_realPath);
	}
	public static void removeRealPath(){
		realPath.remove();
	}
	public static int getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	public static int getPageOffset() {
		return pageOffset.get();
	}
	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	public static String getSort() {
		return sort.get();
	}
	public static void setSort(String _sort) {
		sort.set(_sort);
	}
	public static String getOrder() {
		return order.get();
	}
	public static void setOrder(String _order) {
		order.set(_order);
	}
	public static void removePageSize(){
		pageSize.remove();
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	public static void removeSort(){
		sort.remove();
	}
	public static void removeOrder(){
		order.remove();
	}
}
