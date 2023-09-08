package com.zhongsijia.pojo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Department Class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
  private Integer ID;
  private String name;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
