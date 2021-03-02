package com.binginx.myboot.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoolByteTest {
    public static void main(String[] args) {
        ByteBufAllocator byteBufAllocator = PooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = byteBufAllocator.directBuffer(254);
        byteBuf.writeInt(126);
        log.info("====={}",byteBuf.readInt());
        byteBuf.release();
    }
}
