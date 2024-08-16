package me.ponktacology.imageboard.exception

class InvalidIpAddressException(address: String) : RuntimeException("Invalid IP Address $address")