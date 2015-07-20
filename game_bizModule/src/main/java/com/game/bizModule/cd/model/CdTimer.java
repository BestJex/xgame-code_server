package com.game.bizModule.cd.model;

import java.text.MessageFormat;

import com.game.bizModule.cd.entity.CdTimerEntity;
import com.game.gameServer.io.AbstractPlayerOrSceneIoOper;
import com.game.part.lazySaving.ILazySavingObj;
import com.game.part.util.Assert;

/**
 * 冷却计时器
 * 
 * @author haijiang.jin 
 * @since 2013/4/8
 * 
 */
public class CdTimer implements ILazySavingObj<CdTimerEntity> {
	/** 玩家角色 UId */
	public long _humanUId = -1;
	/** 队列类型 */
	public final CdTypeEnum _cdType;
	/** 开始时间 */
	public long _startTime = -1;
	/** 结束时间 */
	public long _endTime = -1;
	/** 是否已开启 ? */
	public boolean _opened = false;

	/**
	 * 类参数构造器
	 * 
	 * @param cdType 冷却类型
	 * 
	 */
	public CdTimer(CdTypeEnum cdType) {
		// 断言参数不为空
		Assert.notNull(cdType, "cdType");
		// 设置 Cd 类型
		this._cdType = cdType;
	}

	/**
	 * 类参数构造器
	 * 
	 * @param cdType
	 * @param startTime
	 * @param endTime 
	 * 
	 */
	public CdTimer(CdTypeEnum cdType, long startTime, long endTime) {
		this._cdType = cdType;
		this._startTime = startTime;
		this._endTime = endTime;
	}

	/**
	 * 获取时间间隔, 即, 开始时间与结束时间的时间差, 
	 * 单位: ms 毫秒. 
	 *
	 * @return
	 * 
	 */
	public long getDiffTime() {
		return this._endTime - this._startTime;
	}

	/**
	 * 获取时间间隔, 即, 当前时间与结束时间的时间差, 
	 * 单位: ms 毫秒. 
	 * 如果冷却队列的结束时间小于当前时间, 
	 * 则返回 0
	 * 
	 * @param now 
	 * @return
	 * 
	 */
	public long getDiffTime(long now) {
		if (this._endTime < now) {
			return 0;
		} else {
			return this._endTime - now;
		}
	}

	@Override
	public String getStoreKey() {
		return MessageFormat.format(
			"{0}-{1}-{2}",
			this.getClass().getSimpleName(),
			this._cdType.getIntVal(),
			this._humanUId
		);
	}

	@Override
	public CdTimerEntity toEntity() {
		return null;
	}

	@Override
	public String getThreadKey() {
		return AbstractPlayerOrSceneIoOper.getThreadKey(this._humanUId);
	}
}
