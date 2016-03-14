package com.boulevard23.services

import java.util.Base64


class Base64Service extends CodecService {

  override def encode(plain: String): String = {
    new String(Base64.getEncoder.encode(plain.getBytes))
  }

  override def decode(cipher: String): String = {
    new String(Base64.getDecoder.decode(cipher.getBytes))
  }
}
