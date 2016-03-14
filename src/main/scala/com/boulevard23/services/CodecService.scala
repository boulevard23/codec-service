package com.boulevard23.services

trait CodecService {
  def encode(plain: String): String
  def decode(cipher: String): String
}
